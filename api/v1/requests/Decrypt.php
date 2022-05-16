<?php

include $_SERVER['DOCUMENT_ROOT'].'/api/v1/managers/RequestManager.php';

class Decrypt extends BaseRequest {
    private $password;
    private $hash;

    public function __construct($password, $hash) {
        $this->password = $password;
        $this->hash = $hash;
    }

    public function execute(): Result {
        try {
            return RequestManager::decryptRequest($this->password, $this->hash);
        } catch (Exception $ex) {
            return Utils::getDatabaseError();
        }
    }
}

$params = array ('password', 'hash');

if(Utils::validatePostParams($params)) {
    $password = $_POST['password'];
    $hash = $_POST['hash'];

    $request = new Decrypt($password, $hash);
    $result = $request->execute();

    header('Content-Type: application/json');
    echo json_encode($result);
}