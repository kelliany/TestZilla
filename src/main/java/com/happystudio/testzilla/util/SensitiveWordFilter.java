package com.happystudio.testzilla.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.happystudio.testzilla.dao.OptionDao;
import com.happystudio.testzilla.model.Option;

/**
 * 关键词过滤类
 * @author Zhou Yihao
 */
@Component
@Scope(value = "singleton")
public class SensitiveWordFilter {
	/**
	 * 获取本类的单例.
	 * @return 本类的单例
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public synchronized static SensitiveWordFilter getInstance() {
		if (instance == null) {
			instance = new SensitiveWordFilter();
			Option sensitiveWordOption = optionDao.getOption(SENSITIVE_WORD_OPTION_KEY);
			String optionValue = sensitiveWordOption.getOptionValue();
			
			@SuppressWarnings("unchecked")
			ArrayList<String> sensitiveWordList = JSON.parseObject(optionValue, ArrayList.class);
			HashSet<String> sensitiveWordSet = new HashSet<String>(sensitiveWordList);
			instance.addSensitiveWordsToHashMap(sensitiveWordSet);
		}
		return instance;
	}
	
	/**
	 * 提供敏感词过滤的功能
	 * 对sensitiveWordFilter(String, int , String)的重载
	 * 设置了匹配规则(maxMatchType)和替换字符("*")的默认值
	 * @param text - 待过滤字符串
	 * @return 过滤后的字符串
	 */
	public String filter(String text) {
		return filter(text, SensitiveWordFilter.MAX_MATCH_TYPE, "*");
	}
	
	/**
	 * 提供敏感词过滤的功能
	 * @param txt 带过滤字符串
	 * @param matchType 匹配规则，1 为极小匹配，2 为极大匹配
	 * @return 过滤后的字符串
	 */
	private String filter(String text, int matchType, String replaceChar) {
		//获取txt中所有敏感词的位置
		ArrayList<Position> sensitiveWordsPosition = getSensitiveWordsPosition(text, matchType);
		//用replaceChar替换txt中的所有敏感词
		StringBuilder resultStringBuilder = new StringBuilder(text);
		
		Iterator<Position> iterator = sensitiveWordsPosition.iterator();
		while (iterator.hasNext()) {
			Position now = iterator.next();
			resultStringBuilder.replace(now.start, now.start + now.length, 
									getReplaceChars(replaceChar, now.length));
		}
		return resultStringBuilder.toString();
	}
	
	/**
	 * 获取敏感词的位置.
	 * @param text - 待过滤字符串
	 * @param matchType - 匹配规则 1 为极小匹配， 2 为极大匹配
	 * @return 敏感词的位置
	 */
	private ArrayList<Position> getSensitiveWordsPosition(String txt, int matchType) {
		ArrayList<Position> sensitiveWordsPosition = new ArrayList<Position>();
		// 遍历待过滤字符串， 检查 txt 以 i 开始的子串的前缀，是否为敏感词
		for ( int i = 0; i < txt.length(); ++ i ) {
			int length = checkSensitiveWord(txt, i, matchType);
			if (length > 0) {
				Position position = new Position(i, length);
				sensitiveWordsPosition.add(position);
				i = i + length - 1; // 跳过已经匹配的敏感词，因为for中会自增，所以减一
			}
		}
		return sensitiveWordsPosition;
	}
	
	/**
	 * 检查以benginIndex开始的字符串的前缀，是否为敏感词。
	 * @param text - 待过滤文本
	 * @param beginIndex - 此次检查的开始处
	 * @param matchType 匹配模式， 1 为极小匹配， 2 为极大匹配
	 * @return 送存在敏感词，则返回敏感词的长度，若不存在，则返回0
	 */
	@SuppressWarnings("rawtypes")
	private int checkSensitiveWord(String text, int beginIndex, int matchType) {
		/*
		 * matchedLength为当前已匹配的最长敏感词长度
		 * matchingLength为正在尝试匹配的敏感词长度，
		 * 当匹配到终结符时，将matchingLength赋给matchedLength，
		 * 若为极大匹配，则继续匹配，检查当前已匹配到的敏感词是否属于更长的敏感词中，此时，若匹配失败，
		 * 则回退到matchedLength，并结束匹配。
		 */
		int matchedLength = 0;
		int matchingLength = 0;
		
		char nowWord = 0;
		Map nowMap = sensitiveWordMap;
		for (int i = beginIndex; i < text.length(); ++ i) {
			nowWord = text.charAt(i);
			//当前字的follow集
			nowMap = (Map) nowMap.get(nowWord);
			if (nowMap != null) {
				++ matchingLength;
				if ("1".equals(nowMap.get(IS_END))) {	//匹配到终结符，更新当前已匹配的最大长度
					 matchedLength = matchingLength;
					 if (SensitiveWordFilter.MIN_MATCH_TYPE == matchType) {
						 //若为极小匹配规则，那么一旦匹配到终结符，就结束匹配
						 break;
					 }
				}
			}
			else {	//当前字的follow集若为空，则匹配结束，
				break;
			}
		}
		return matchedLength;
	}
	
	/**
	 * 获取用以替换敏感词的字符串 ，敏感词中的所有字都被替换成replaceChar，
	 * 根据长度和字符生成，例如"****"
	 * @param replaceChar 用来替换敏感词的字符
	 * @param length 敏感词的长度
	 * @return 用来替换敏感词的字符串
	 */
	private static String getReplaceChars(String replaceChar, int length) {
		String resultChars = "";
		for (int i = 0; i < length; ++ i) {
			resultChars += replaceChar;
		}
		return resultChars;
	}
	
	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @param sensitiveWordSet - 敏感词的Set集
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addSensitiveWordsToHashMap(Set<String> sensitiveWordSet) {
		// 初始化敏感词HashMap, 大小与Set相等.
		sensitiveWordMap = new HashMap((int)(sensitiveWordSet.size() * 1.5));
		
		// 在 nowMap状态读过key，到达新状态，故创建newWordMap
		String key = null;
		Map nowMap = null;
		Map newWordMap = null;
		//迭代SensitiveWordSet
		Iterator<String> iterator = sensitiveWordSet.iterator();
		while (iterator.hasNext()) {
			key = iterator.next();
			//从最高层的HashMap开始遍历
			nowMap = sensitiveWordMap;
			
			//迭代当前敏感词中的每个字
			for (int i = 0; i < key.length(); ++ i) {
				char keyChar = key.charAt(i);
				Object wordMap = nowMap.get(keyChar);
				
				if (wordMap != null) {
					//当前敏感词的当前字已存在,进入下一个状态
					nowMap = (Map) wordMap;
				} else {	
					//当前敏感词的当前字不存在，创建一个新Map(状态)
					newWordMap = new HashMap();
					//默认当前敏感词的当前字不是终结符
					newWordMap.put(IS_END, "0");
					//将新建的Map(状态)添加到当前Map(状态)中
					nowMap.put(keyChar, newWordMap);
					//读取当前字后进入下一个状态
					nowMap = newWordMap;
				}
				if (i == key.length() - 1) {
					nowMap.put(IS_END, "1");
				}
			}

		}
	}
	
	/**
	 * 单例模式中私有化的构造方法. 
	 */
	private SensitiveWordFilter() {}
	
	/**
	 * 本类的单例.
	 */
	@Autowired
	private static SensitiveWordFilter instance;
	
	/**
	 * 存储敏感词的HashMap. 
	 */
	private HashMap<?, ?> sensitiveWordMap;
	
	/**
	 * HashMap中敏感词是否终结的标识. 
	 */
	private static final String IS_END = "isEnd";
	
	/**
	 * minMatchType 极小匹配规则 1 若找到敏感词的终结符，则不再继续寻找其是否包含在一个更长的敏感词中  
	 * maxMatchType 极大匹配规则 2 若找到敏感词的终结符，则仍需继续寻找其是否包含在一个更长的敏感词中
	 */
	public static final int MIN_MATCH_TYPE = 1;
	public static final int MAX_MATCH_TYPE = 2;
	
	/**
	 * 敏感词文件的编码格式 
	 */
	public static final String ENCODING = "UTF-8";
	
	/**
	 * 敏感词系统设置项. 
	 */
	public static final String SENSITIVE_WORD_OPTION_KEY = "SensitiveWord";
	
	/**
	 *  自动注入OptionDao对象
	 */
	@Autowired
	private static OptionDao optionDao;
}

/**
 * 存储敏感词的位置(开始位置和长度)
 * @author Zhou YiHao
 */
class Position {
		/**
		 * Position的构造函数.
		 * @param start - 敏感词开始位置
		 * @param length - 敏感词长度
		 */
		public Position(int start, int length) {
			this.start = start;
			this.length = length;
		}
		
		int start;
		
		int length;
	}