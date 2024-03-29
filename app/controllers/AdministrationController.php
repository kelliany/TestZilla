<?php

use Phalcon\Http\Response;
use Phalcon\Logger;
use Phalcon\Logger\Adapter\File as FileAdapter;
use Phalcon\Mvc\View;

/**
 * The controller used for handling system management.
 * 
 * @package TestZilla\controller\AdministrationController
 * @author Xie Haozhe <zjhzxhz@gmail.com>
 */
class AdministrationController extends BaseController {
    /**
     * Initialize the Controller
     */
    public function initialize() {
        parent::__initialize();
        $logDir        = $this->config->application->logDir;
        $this->logger  = new FileAdapter(APP_PATH . "/{$logDir}/TestZilla.log");

        $this->view->setRenderLevel(View::LEVEL_LAYOUT);
    }

    public function indexAction() {
        var_dump('Hello');
        $this->tag->prependTitle($this->localization['administration.index.title']);
    }

    /**
     * The logger of AdministrationController.
     * @var Phalcon\Logger\Adapter\File
     */
    private $logger;
}