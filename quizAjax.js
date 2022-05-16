let myTimeout;
let score = 0;
let passScore = 0;
let MaxScore = 0;
$(document).ready(function () {
  $(".container").hide();
  $("h3").hide();
  GetQuiz();
  myTimeout = setTimeout(start, 30000);
  //final();
});
/////jump the intro
$("#btnStart").click(function () {
  start();
});
function start() {
  clearTimeout(myTimeout);
  $("#intro").hide();
  $(".card").hide();
  $("#btnStart").hide();
  $("#logo").hide();
  $("#board").fadeOut();
  $("h3").show();
  $(".container").fadeIn();
  $("#userScore").text(0);
  showQuestion(0);
}
let currentQuestion = 0;
function showQuestion(currentQuestion) {
  $(`#box_${currentQuestion}`).fadeIn();
}
function GetQuiz() {
  let data = {
    action: "getAll",
  };
  $.ajax({
    url: "https://juno.codequark.com/api/v1/requests/QuestionAction.php",
    method: "POST",
    data: data,
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    success: function (r) {
      const questions = r.response.questions;
      maxScore = questions.length;
      //console.log(r);
      $("#numberOfQuestions").text(maxScore);

      passScore = maxScore * 0.6;
      for (let i = 0; i < maxScore; i++) {
        let options = questions[i].options;
        let correctAnswer = questions[i].answerId;
        $("#feed").append(
          `<div id="box_${i}" class="card border-warning mb-3 bg-transparent" style="max-width: 18rem">` +
            `<form id="form${questions[i].questionId}">` +
            '<div class="card-body text-warning">' +
            '<h3 class="card-title">' +
            `<p class="text-white">${i + 1}) ${questions[i].questionText}</p>` +
            "</h3>" +
            `<div id="optionFeed${questions[i].questionId}">` +
            "</div>" +
            "</div>" +
            '<div class="card-footer bg-transparent border-danger d-flex justify-content-center">' +
            '<button type="submit" class="btn btn-outline-info">Send</button>' +
            "</div>" +
            `</form>` +
            "</div>"
        );
        for (let i2 = 0; i2 < options.length; i2++) {
          $(`#optionFeed${questions[i].questionId}`).append(
            `<div class="form-check"> 
              <input class="form-check-input" type="radio" name="question${i}" value="${options[i2].optionId}" id="option${options[i2].optionId}">
              <label class="form-check-label " for="option${options[i2].optionId}">
              ) ${options[i2].optionText}
              </label>
              </div>`
          );
          //console.log(options[i2].optionId);
        }
        let userAnswer;
        $(`#form${questions[i].questionId}`).on("submit", function (event) {
          event.preventDefault();
          userAnswer = $(`input[name='question${i}']:checked`).val();
          //console.log(userAnswer);
          if (userAnswer) {
            questionCheck(correctAnswer, userAnswer);
          }
        });
      }
    },
  });
}

function questionCheck(correctAnswer, userAnswer) {
  if (correctAnswer == userAnswer) {
    alert("correct");
    score++;
  } else {
    alert("incorrect");
  }
  $("#userScore").text(score);
  $("input").prop("checked", false);
  $(`#box_${currentQuestion}`).fadeOut();
  currentQuestion++;
  $(`#box_${currentQuestion}`).fadeIn();

  ///// maxScore is the same as number of questions
  if (currentQuestion === maxScore) {
    final();
  }
}

function final() {
  if (score === maxScore) {
    $("#titleFinal").text("Congratulations Jedi Master!");
    $("#subtitleFinal").text(
      "The prophecy is true! You have save the galaxy and bring balance to the Force!"
    );
  } else if (score >= passScore) {
    $("#titleFinal").text("Congratulations Padawan!");
    $("#subtitleFinal").text("Great, kid, don't get cocky!");
  } else {
    $("#titleFinal")
      .text("I have a bad feeling about this.")
      .addClass("text-danger");
    $("#subtitleFinal").text(
      "Once you start down the dark path, forever will it dominate your destiny"
    );
    $("#footerfinal").append(
      '<button id="retakeBtn" class="btn btn-outline-danger">' +
        "Retake" +
        "</button>"
    );
  }
  $("#boxfinal").show();
}
