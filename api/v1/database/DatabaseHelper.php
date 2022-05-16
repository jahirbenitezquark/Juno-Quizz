<?php

class DatabaseHelper {
    const queryGetLastId = "SELECT @@IDENTITY AS id";

    // Get Models by ID
    const queryGetOptionById = "SELECT * FROM OptionsV1 WHERE OptionId = :id";
    const queryGetQuestionById = "SELECT * FROM QuestionsV1 WHERE QuestionId = :id";

    // Insert Model
    const queryInsertOptions = "INSERT INTO OptionsV1 (OptionText, QuestionId) VALUES (':optionText', :questionId)";
    const queryInsertQuestions = "INSERT INTO QuestionsV1 (QuestionText, AnswerId) VALUES (':questionText', :answerId)";

    // Update Models
    const queryUpdateOption = "UPDATE OptionsV1 SET OptionText = ':optionText', QuestionId = :questionId, Active = ':active' WHERE OptionId = :id";
    const queryUpdateQuestion = "UPDATE QuestionsV1 SET QuestionText = ':questionText', AnswerId = :answerId, Active = ':active' WHERE QuestionId = :id";

    // Delete Models
    const queryDeleteOption = "DELETE FROM OptionsV1 WHERE OptionId = :id";
    const queryDeleteQuestion = "DELETE FROM QuestionsV1 WHERE QuestionId = :id";

    // GetItems
    const queryGetOptions = "SELECT * FROM OptionsV1 WHERE QuestionId = :questionId AND Active = 1";
    const queryGetQuestions = "SELECT * FROM QuestionsV1 WHERE Active = 1";
    const queryGetProducts = "SELECT * FROM ProductsV2 WHERE Active = 1";
    const queryGetSizes = "SELECT * FROM SizesV2 WHERE ProductId = :productId AND Active = 1";
}