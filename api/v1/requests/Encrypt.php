<?php

include $_SERVER['DOCUMENT_ROOT'].'/api/v1/managers/RequestManager.php';

class Encrypt extends BaseRequest {
    private $text;

    public function __construct($text) {
        $this->text = $text;
    }

    public function execute(): Result {
        try {
            return RequestManager::encryptRequest($this->text);
        } catch (Exception $ex) {
            return Utils::getDatabaseError();
        }
    }
}

$params = array ('text');

if(Utils::validatePostParams($params)) {
    $text = $_POST['text'];

    $request = new Encrypt($text);
    $result = $request->execute();

    header('Content-Type: application/json');
    echo json_encode($result);
}