<?php

include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/application/BaseRequest.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/application/Security.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/database/DatabaseHelper.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/helpers/SQLServerWrapper.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/managers/SQLServerManager.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/models/v1/OptionV1.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/models/v1/QuestionV1.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/models/v2/ProductV2.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/models/v2/SizeV2.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/models/Result.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/utils/Constants.php';
include $_SERVER['DOCUMENT_ROOT'] . '/api/v1/utils/Utils.php';

class RequestManager {
    public static function encryptRequest($text): Result {
        $result = new Result();

        $result->setCode(Constants::successCode);
        $result->setMessage(Constants::successCodeDescription);
        $result->setResponse(self::encrypt($text));

        return $result;
    }

    public static function encrypt($text): string {
        return Security::encrypt($text);
    }

    public static function decryptRequest($password, $hash): Result {
        $result = new Result();

        $result->setCode(Constants::successCode);
        $result->setMessage(Constants::successCodeDescription);
        $result->setResponse(self::decrypt($password, $hash));

        return $result;
    }

    public static function decrypt(string $password, string $hash): bool {
        return Security::decrypt($password, $hash);
    }

    public static function getOptionsV1(int $questionId): Result {
        $result = new Result();

        $query = DatabaseHelper::queryGetOptions;
        $query = str_replace(Constants::replaceQuestionId, $questionId, $query);

        $statement = SQLServerManager::getInstance()->executeQuery($query);

        $response = array();

        while($row = SQLServerManager::getInstance()->fetch($statement)) {
            $model = SQLServerWrapper::toOptionV1($row);
            $item = $model->toArray();

            $response[] = $item;
        }

        $response = array(Constants::options => $response);

        $result->setCode(Constants::successCode);
        $result->setMessage(Constants::successCodeDescription);
        $result->setResponse($response);

        return $result;
    }

    public static function getOptionListV1(int $questionId): array {
        $query = DatabaseHelper::queryGetOptions;
        $query = str_replace(Constants::replaceQuestionId, $questionId, $query);

        $statement = SQLServerManager::getInstance()->executeQuery($query);

        $response = array();

        while($row = SQLServerManager::getInstance()->fetch($statement)) {
            $model = SQLServerWrapper::toOptionV1($row);
            $item = $model->toArray();

            $response[] = $item;
        }

        return $response;
    }

    public static function getQuestionsV1(): Result {
        $result = new Result();

        $query = DatabaseHelper::queryGetQuestions;

        $statement = SQLServerManager::getInstance()->executeQuery($query);

        $response = array();

        while($row = SQLServerManager::getInstance()->fetch($statement)) {
            $model = SQLServerWrapper::toQuestionV1($row);
            $options = self::getOptionListV1($model->getQuestionId());
            $model->setOptions($options);

            $item = $model->toArray();

            $response[] = $item;
        }

        $response = array(Constants::questions => $response);

        $result->setCode(Constants::successCode);
        $result->setMessage(Constants::successCodeDescription);
        $result->setResponse($response);

        return $result;
    }

    public static function getProductsV2(): Result {
        $result = new Result();

        $query = DatabaseHelper::queryGetProducts;

        $statement = SQLServerManager::getInstance()->executeQuery($query);

        $response = array();

        while($row = SQLServerManager::getInstance()->fetch($statement)) {
            $model = SQLServerWrapper::toProductV2($row);
            $item = $model->toArray();

            $response[] = $item;
        }

        $response = array(Constants::products => $response);

        $result->setCode(Constants::successCode);
        $result->setMessage(Constants::successCodeDescription);
        $result->setResponse($response);

        return $result;
    }

    public static function getSizesV2(int $productId): Result {
        $result = new Result();

        $query = DatabaseHelper::queryGetSizes;
        $query = str_replace(Constants::replaceProductId, $productId, $query);

        $statement = SQLServerManager::getInstance()->executeQuery($query);

        $response = array();

        while($row = SQLServerManager::getInstance()->fetch($statement)) {
            $model = SQLServerWrapper::toSizeV2($row);
            $item = $model->toArray();

            $response[] = $item;
        }

        $response = array(Constants::sizes => $response);

        $result->setCode(Constants::successCode);
        $result->setMessage(Constants::successCodeDescription);
        $result->setResponse($response);

        return $result;
    }
}