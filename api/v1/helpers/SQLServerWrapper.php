<?php

class SQLServerWrapper {
	public static function toOptionV1($row): OptionV1 {
		$model = new OptionV1();

        $model->setOptionId(intval($row[Constants::columnOptionId]));
        $model->setOptionText(strval($row[Constants::columnOptionText]));
        $model->setQuestionId(intval($row[Constants::columnQuestionId]));
        $model->setActive(boolval($row[Constants::columnActive]));

		return $model;
	}

    public static function toQuestionV1($row): QuestionV1 {
        $model = new QuestionV1();

        $model->setQuestionId(intval($row[Constants::columnQuestionId]));
        $model->setQuestionText(strval($row[Constants::columnQuestionText]));
        $model->setAnswerId(intval($row[Constants::columnAnswerId]));
        $model->setActive(boolval($row[Constants::columnActive]));

        return $model;
    }

    public static function toProductV2($row): ProductV2 {
        $model = new ProductV2();

        $model->setProductId(intval($row[Constants::columnProductId]));
        $model->setProductName(strval($row[Constants::columnProductName]));
        $model->setProductPrice(floatval($row[Constants::columnProductPrice]));
        $model->setFamilyId(intval($row[Constants::columnFamilyId]));
        $model->setRate(intval($row[Constants::columnRate]));
        $model->setSale(boolval($row[Constants::columnSale]));
        $model->setOldPrice(floatval($row[Constants::columnOldPrice]));
        $model->setImageURL(strval($row[Constants::columnImageURL]));
        $model->setActive(boolval($row[Constants::columnActive]));

        return $model;
    }

    public static function toSizeV2($row): SizeV2 {
        $model = new SizeV2();

        $model->setSizeId(intval($row[Constants::columnSizeId]));
        $model->setSizeText(strval($row[Constants::columnSizeText]));
        $model->setProductId(intval($row[Constants::columnProductId]));
        $model->setActive(boolval($row[Constants::columnActive]));

        return $model;
    }
}