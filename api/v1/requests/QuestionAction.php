<?php

include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/managers/RequestManager.php';

class QuestionAction extends BaseRequest {
    private string $action;

    public function __construct(string $action) {
        $this->action = $action;
    }

    public function execute(): Result {
        try {
            switch ($this->action) {
                case Constants::GET_ALL: {
                    return RequestManager::getQuestionsV1();
                }

                case Constants::GET_MODEL: {
                    $params = array(Constants::questionId);
                    $error = Utils::validateParams($params);

                    if($error === null) {
                        $questionId = $_POST[Constants::questionId];

                        return RequestManager::getQuestionV1($questionId);
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
    $request = new QuestionAction($action);
    $result = $request->execute();

    header(Constants::contentApplicationJson);
    echo json_encode($result);
}