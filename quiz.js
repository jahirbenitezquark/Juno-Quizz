let myTimeout;
let score = 0;
let passScore = 0;
let MaxScore = 0;
let currentQuestion = 0;
$(document).ready(function () {
  //hide the quiz container to let the intro shown
  $("#quizContainer, #score").hide();
  //render the questions and options on its own div card
  GetQuiz();

  myTimeout = setTimeout(start, 30000);
});
/////jump the intro
$("#btnStart").click(function () {
  start();
});

//hides the intro elements, the questions cards and shows only the fist card
function start() {
  clearTimeout(myTimeout);
  $("#board").fadeOut();
  $(".intro, .card, #btnStart, .logo").hide();
  $("#quizContainer").fadeIn();
  //set the score span to 0
  $("#userScore").text(0);
  $("#score").show();
  showQuestion(currentQuestion);
}

function showQuestion(currentQuestion) {
  $(`#box_${currentQuestion}`).fadeIn("slow");
}
function GetQuiz() {
  let data = {
    action: "getAll",
  };
  $.ajax({
    url: "/api/v1/requests/QuestionAction.php",
    method: "POST",
    data: data,
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    success: function (r) {
      const questions = r.response.questions;
      maxScore = questions.length;
      $("#numberOfQuestions").text(questions.length);
      passScore = maxScore * 0.6;
      for (let i = 0; i < questions.length; i++) {
        let options = questions[i].options;

        let correctAnswer = questions[i].answerId;
        //render question cards
        $("#feed").append(
          `<div id="box_${i}" class="card border-warning mb-3 bg-transparent" style="max-width: 80%">
        <form id="form${questions[i].questionId}">
          <div class="card-body text-warning text-center">
            <h3 class="card-title">
              <p class="text-white">${i + 1}) ${questions[i].questionText}</p>
            </h3>
            <div id="optionFeed${questions[i].questionId}">
            </div>
          </div>
          <div class="card-footer bg-transparent border-danger d-flex justify-content-center">
            <button type="submit" class="btn btn-outline-info">Send</button>
          </div>
        </form>
      </div>`
        );
        //render input options on each question card
        for (let i2 = 0; i2 < options.length; i2++) {
          $(`#optionFeed${questions[i].questionId}`).append(
            `<div class="form-check d-flex justify-content-center">
          <input class="form-check-input" type="radio" name="question${i}" value="${options[i2].optionId}" id="option${options[i2].optionId}">
          <label class="form-check-label " for="option${options[i2].optionId}">
            ) ${options[i2].optionText}
          </label>
        </div>`
          );
          //console.log(options[i2].optionId);
        }
        let userAnswer;
        //creating a submit event for each question card
        $(`#form${questions[i].questionId}`).on("submit", function (event) {
          event.preventDefault();
          userAnswer = $(`input[name='question${i}']:checked`).val();
          //console.log(userAnswer);
          if (userAnswer) {
            questionCheck(correctAnswer, userAnswer, i);
          }
        });
      }
    },
  });
}

function questionCheck(correctAnswer, userAnswer, i) {
  if (correctAnswer == userAnswer) {
    alert("Correct!");
    score++;
  } else {
    alert("Incorrect");
  }
  $(`input[name='question${i}']:checked`).addClass("text-success");
  //set current user score
  $("#userScore").text(score);
  //idk why but if i dont uncheck the input its bugged
  $("input").prop("checked", false);
  //hide current question card and shows the next one
  $(`#box_${currentQuestion}`).hide();
  currentQuestion++;
  showQuestion(currentQuestion);

  ///// maxScore is the same as number of questions
  if (currentQuestion === maxScore) {
    final();
  }
}

function final() {
  const $titleFinal = $("#titleFinal");
  const $subtitleFinal = $("#subtitleFinal");
  let imgSrc = "";
  if (score === maxScore) {
    $titleFinal.text("Congratulations Jedi Master!");
    $subtitleFinal.text(
      "The prophecy is true! You have save the galaxy and bring balance to the Force!"
    );
    imgSrc = "https://i.gifer.com/9MIU.gif";
  } else if (score >= passScore) {
    $titleFinal.text("Congratulations Padawan!");
    $subtitleFinal.text("“Rebellions are built on hope.” —Jyn, Rogue One");
    imgSrc =
      "https://i.pinimg.com/originals/ae/6c/c8/ae6cc83fb631049e4586d0d92e5cea23.gif";
  } else {
    $titleFinal
      .text("I have a bad feeling about this.")
      .addClass("text-danger");
    $subtitleFinal.text(
      "“Once you start down the dark path, forever will it dominate your destiny.” —Yoda, The Empire Strikes Back "
    );
    imgSrc =
      "https://i.pinimg.com/originals/ce/01/85/ce018548463c43fb9cbb554dbfd0b710.gif";
  }
  $("#boxfinal").show();
  $("img").attr("src", imgSrc);
}

$("#retakeBtn").click(function () {
  currentQuestion = 0;
  score = 0;
  start();
});
