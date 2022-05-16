<?php

include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/managers/RequestManager.php';

class SizeAction extends BaseRequest {
    private string $action;

    public function __construct(string $action) {
        $this->action = $action;
    }

    public function execute(): Result {
        try {
            switch ($this->action) {
                case Constants::GET_ALL: {
                    $params = array(Constants::productId);
                    $error = Utils::validateParams($params);

                    if($error === null) {
                        $productId = $_POST[Constants::productId];

                        return RequestManager::getSizesV2($productId);
                    }

                    break;
                }

                case Constants::GET_MODEL: {
                    $params = array(Constants::sizeId);
                    $error = Utils::validateParams($params);

                    if($error === null) {
                        $sizeId = $_POST[Constants::sizeId];

                        return RequestManager::getSizeV2($sizeId);
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
    $request = new SizeAction($action);
    $result = $request->execute();

    header(Constants::contentApplicationJson);
    echo json_encode($result);
}