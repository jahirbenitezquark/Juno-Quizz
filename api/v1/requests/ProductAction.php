<?php

include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/managers/RequestManager.php';

class ProductAction extends BaseRequest {
    private string $action;

    public function __construct(string $action) {
        $this->action = $action;
    }

    public function execute(): Result {
        try {
            switch ($this->action) {
                case Constants::GET_ALL: {
                    return RequestManager::getProductsV2();
                }

                case Constants::GET_MODEL: {
                    $params = array(Constants::productId);
                    $error = Utils::validateParams($params);

                    if($error === null) {
                        $productId = $_POST[Constants::productId];

                        return RequestManager::getProductV2($productId);
                    }
                    break;
                }

                default: {
                    return Utils::getUnknownAction();
                }
            }
        } catch (Exception $ex) {
            return Utils::getDatabaseError($ex->__toString());
        }

        return Utils::getMissingParamsError($error);
    }
}

$params = array(Constants::action);
$action = $_POST[Constants::action];

if(Utils::validatePostParams($params)) {
    $request = new ProductAction($action);
    $result = $request->execute();

    header(Constants::contentApplicationJson);
    echo json_encode($result);
}