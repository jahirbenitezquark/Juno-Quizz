<?php

class Constants {
    const GET_ALL = "getAll";
    const GET_MODEL = "getModel";
    const INSERT = "insert";
    const UPDATE = "update";
    const DELETE = "delete";
    const ENABLE = "enable";
    const DISABLE = "disable";
    const USER = "usuario";
    const ENCRYPT = "encrypt";
    const DECRYPT = "decrypt";
    const LOGIN = "login";
    const LOGOUT = "logout";
    const GET_LOGIN = "getLogin";

    /* * * * * * * * * * */
    // Constants for database model columns
    /* * * * * * * * * * */

    // OptionsV1
    const columnOptionId = "OptionId";
    const columnOptionText = "OptionText";
    const columnActive = "Active";

    // QuestionsV1
    const columnQuestionId = "QuestionId";
    const columnQuestionText = "QuestionText";
    const columnAnswerId = "AnswerId";

    // ProductV2
    const columnProductId = "ProductId";
    const columnProductName = "ProductName";
    const columnProductPrice = "ProductPrice";
    const columnFamilyId = "FamilyId";
    const columnRate = "Rate";
    const columnSale = "Sale";
    const columnOldPrice = "OldPrice";
    const columnImageURL = "ImageURL";

    // SizeV2
    const columnSizeId = "SizeId";
    const columnSizeText = "SizeText";

    /* * * * * * * * * * */
    // Constants for replace query
    /* * * * * * * * * * */

    // OptionsV1
    const replaceOptionId = ":optionId";
    const replaceOptionText = ":optionText";
    const replaceActive = ":active";

    // QuestionsV1
    const replaceQuestionId = ":questionId";
    const replaceQuestionText = ":questionText";
    const replaceAnswerId = ":answerId";

    // SizeV2
    const replaceProductId = ":productId";

    /* * * * * * * * * * */
    // Constants for params
    /* * * * * * * * * * */

    // General
    const action = 'action';
    const hash = 'hash';
    const code = 'code';
    const message = 'message';
    const response = 'response';

    /* * * * * * * * * * */
    // Constants for intern model
    /* * * * * * * * * * */

    // Models
    const option = 'option';
    const options = 'options';

    const question = 'question';
    const questions = 'questions';

    const product = 'product';
    const products = 'products';

    const size = 'size';
    const sizes = 'sizes';

    // General
    const id = 'id';
    const unknown = 'unknown';

    // OptionsV1
    const optionId = "optionId";
    const optionText = "optionText";
    const active = "active";

    // QuestionsV1
    const questionId = "questionId";
    const questionText = "questionText";
    const answerId = "answerId";

    // ProductV2
    const productId = "productId";
    const productName = "productName";
    const productPrice = "productPrice";
    const familyId = "familyId";
    const rate = "rate";
    const sale = "sale";
    const oldPrice = "oldPrice";
    const imageURL = "imageURL";

    // SizeV2
    const sizeId = "sizeId";
    const sizeText = "sizeText";

    // Date Time format
    const timestampFormat = "Y-m-d H:i:s";

    // Content-Types
    const contentApplicationJson = 'Content-Type: application/json';

    // Success or error codes for API
    const successCode = 0;
    const successCodeDescription = "success";

    const errorCode = -1;
    const errorCodeDescription = "error";

    const errorCodeMissingParams = -2;
    const errorCodeMissingParamsDescription = "error_missing_params";

    const errorCodeConnectionDatabase = -3;
    const errorCodeConnectionDatabaseDescription = "error_connection_database";

    const errorCodeNotAuthenticated = -4;
    const errorCodeNotAuthenticatedDescription = "error_not_authenticated";

    const errorCodeUnknownAction = -5;
    const errorCodeUnknownActionDescription = "error_unknown_action";

    const errorCodeDuplicateEntry = -6;
    const errorCodeDuplicateEntryDescription = "duplicate_entry";

    // Error codes for model

    const errorCodeExists = -50;
    const errorCodeExistsDescription = "error_exists";

    const errorCodeNotExists = -51;
    const errorCodeNotExistsDescription = "error_not_exists";

    const errorCodeEnabled = -52;
    const errorCodeEnabledDescription = "error_enabled";

    const errorCodeDisabled = -53;
    const errorCodeDisabledDescription = "error_disabled";
}