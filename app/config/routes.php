<?php

use Phalcon\Mvc\Router;

/**
 * The global router of the application.
 *
 * @author Xie Haozhe <zjhzxhz@gmail.com>
 */
$router = new Router();

/* Routers for DefaultController */
$router->add('/', array(
    'controller' => 'default',
    'action'     => 'index',
));
$router->add('/getCsrfToken.action', array(
    'controller' => 'default',
    'action'     => 'getCsrfToken',
));
$router->add('/terms', array(
    'controller' => 'default',
    'action'     => 'terms',
));
$router->add('/privacy', array(
    'controller' => 'default',
    'action'     => 'privacy',
));
$router->add('/changeLanguage.action', array(
    'controller' => 'default',
    'action'     => 'changeLanguage',
));

/* Routers for ErrorsController */
$router->add('/not-supported', array(
    'controller' => 'errors',
    'action'     => 'notSupportedError',
));

/* Routers for AccountsController */
$router->add('/accounts/signin', array(
    'controller' => 'accounts',
    'action'     => 'signIn',
));
$router->add('/accounts/signin.action', array(
    'controller' => 'accounts',
    'action'     => 'doSignIn',
));
$router->add('/accounts/signup', array(
    'controller' => 'accounts',
    'action'     => 'signUp',
));
$router->add('/accounts/signup.action', array(
    'controller' => 'accounts',
    'action'     => 'doSignUp',
));
$router->add('/accounts/verify-email', array(
    'controller' => 'accounts',
    'action'     => 'verifyEmail', 
));
$router->add('/accounts/signout', array(
    'controller' => 'accounts',
    'action'     => 'signOut',
));
$router->add('/accounts/reset-password', array(
    'controller' => 'accounts',
    'action'     => 'resetPassword',
));
$router->add('/accounts/forgotPassword.action', array(
    'controller' => 'accounts',
    'action'     => 'doForgotPassword',
));
$router->add('/accounts/resetPassword.action', array(
    'controller' => 'accounts',
    'action'     => 'doResetPassword',
));
$router->add('/user/{uid}', array(
    'controller' => 'accounts',
    'action'     => 'user',
));
$router->add('/user/{uid}/getIssues.action', array(
    'controller' => 'accounts',
    'action'     => 'getIssues',
));

/* Routers for DashboardController */
$router->add('/dashboard', array(
    'controller' => 'dashboard',
    'action'     => 'index',
));
$router->add('/dashboard/profile', array(
    'controller' => 'dashboard',
    'action'     => 'profile',
));
$router->add('/dashboard/changePassword.action', array(
    'controller' => 'dashboard',
    'action'     => 'changePassword',
));
$router->add('/dashboard/updateProfile.action', array(
    'controller' => 'dashboard',
    'action'     => 'updateProfile',
));
$router->add('/dashboard/products', array(
    'controller' => 'dashboard',
    'action'     => 'products',
));
$router->add('/dashboard/getProducts.action', array(
    'controller' => 'dashboard',
    'action'     => 'getProducts',
));
$router->add('/dashboard/getProduct.action', array(
    'controller' => 'dashboard',
    'action'     => 'getProduct',
));
$router->add('/dashboard/createProduct.action', array(
    'controller' => 'dashboard',
    'action'     => 'createProduct',
));
$router->add('/dashboard/editProduct.action', array(
    'controller' => 'dashboard',
    'action'     => 'editProduct',
));
$router->add('/dashboard/issues', array(
    'controller' => 'dashboard',
    'action'     => 'receivedIssues',
));
$router->add('/dashboard/issues/received', array(
    'controller' => 'dashboard',
    'action'     => 'receivedIssues',
));
$router->add('/dashboard/getReceivedIssues.action', array(
    'controller' => 'dashboard',
    'action'     => 'getReceivedIssues', 
));
$router->add('/dashboard/issues/submitted', array(
    'controller' => 'dashboard',
    'action'     => 'submittedIssues',
));
$router->add('/dashboard/getSubmittedIssues.action', array(
    'controller' => 'dashboard',
    'action'     => 'getSubmittedIssues', 
));

/* Routers for ProductsController */
$router->add('/products', array(
    'controller' => 'products',
    'action'     => 'index',
));
$router->add('/products/getProducts.action', array(
    'controller' => 'products',
    'action'     => 'getProducts',
));
$router->add('/product/{productId}', array(
    'controller' => 'products',
    'action'     => 'product',
));
$router->add('/product/{productId}/getIssues.action', array(
    'controller' => 'products',
    'action'     => 'getIssues',
));
$router->add('/product/{productId}/new-issue', array(
    'controller' => 'products',
    'action'     => 'newIssue',
));
$router->add('/product/{productId}/createIssue.action', array(
    'controller' => 'products',
    'action'     => 'createIssue',
));
$router->add('/issue/{issueId}', array(
    'controller' => 'products',
    'action'     => 'issue',
));
$router->add('/issue/{issueId}/getIssueReplies.action', array(
    'controller' => 'products',
    'action'     => 'getIssueReplies',
));
$router->add('/issue/{issueId}/createIssueReply.action', array(
    'controller' => 'products',
    'action'     => 'createIssueReply',
));

/* Routers for AdministrationController */
$router->add('/administration', array(
    'controller' => 'administration',
    'action'     => 'index',
));

$router->handle();
