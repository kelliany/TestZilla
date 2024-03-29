<?php

use Phalcon\Mvc\View;

/**
 * The error handling controller for the application.
 * 
 * @package TestZilla\controller\ErrorsController
 * @author Xie Haozhe <zjhzxhz@gmail.com>
 */
class ErrorsController extends BaseController {
    /**
     * Initialize the Controller
     * @return 
     */
    public function initialize() {
        parent::__initialize();
    }

    /**
     * Render to the 404 page.
     */
    public function resourceNotFoundAction() { 
        $this->tag->prependTitle($this->localization['errors.404.title']);
    }

    /**
     * Render to the 500 page.
     */
    public function internalServerErrorAction() { 
        $this->tag->prependTitle($this->localization['errors.500.title']);
    }

    /**
     * Render to the upgrade browser page.
     */
    public function notSupportedErrorAction() { 
        $this->tag->prependTitle($this->localization['errors.not-supported.title']);

        // Disable the layout for this page.        
        $this->view->setRenderLevel(View::LEVEL_ACTION_VIEW);
    }
}
