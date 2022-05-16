<?php

include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/managers/RequestManager.php';

class OptionAction extends BaseRequest {
    private string $action;

    public function __construct(string $action) {
        $this->action = $action;
    }

    public function execute(): Result {
        try {
            switch ($this->action) {
                case Constants::GET_ALL: {
                    $params = array(Constants::questionId);
                    $error = Utils::validateParams($params);

                    if($error === null) {
                        $questionId = $_POST[Constants::questionId];

                        return RequestManager::getOptionsV1($questionId);
                    }

                    break;
                }

                case Constants::GET_MODEL: {
                    $params = array(Constants::optionId);
                    $error = Utils::validateParams($params);

                    if($error === null) {
                        $optionId = $_POST[Constants::optionId];

                        return RequestManager::getOptionV1($optionId);
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
    $request = new OptionAction($action);
    $result = $request->execute();

    header(Constants::contentApplicationJson);
    echo json_encode($result);
}