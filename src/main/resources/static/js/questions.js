$(document).ready(function() {
	setSelectOnChange();
	setNoAnswerOnChange();
	setModalOnShow();
	setNewQuestionOnClick()
});

/**
 * Sets the onChange behavior of the select boxes "question type" and "number of choices"
 */
function setSelectOnChange() {
	$("select").filter(function() {
		return this.id.match(/(question-type|choices-number)-.+/);
	}).unbind('change').change(function() {
		var selectBox = $(this);

		if (selectBox.attr('id').match(/question-type-.+/)) {
			var panelBody = selectBox.closest(".panel-body");
			replaceQuestionType(selectBox, panelBody);
		} else if (selectBox.attr('id').match(/choices-number-.+/)) {
			var panelBody = selectBox.closest(".panel-body");
			replaceChoices(selectBox, panelBody);
		}
	});
}

/**
 * Redraws the panel body of question depending on the chosen question type
 * 
 * @param questionTypeSelectBox
 * @param panelBody
 */
function replaceQuestionType(questionTypeSelectBox, panelBody) {
	switch (questionTypeSelectBox.val()) {
	case "Textfrage":
		drawTextQuestion(panelBody);
		break;
	case "Best First":
		drawBestFirst(panelBody);
		break;
	case "Best In The Middle":
		drawBestInTheMiddle(panelBody);
	}
}

/**
 * Redraws the choices of a multiple choice question depending on the selected number of choices and question
 * type
 * 
 * @param choicesNumberSelectBox
 * @param panelBody
 */
function replaceChoices(choicesNumberSelectBox, panelBody) {
	var choicesPanel = panelBody.find('.panel');
	var questionNumber = panelBody.attr('id').split("-").pop();
	var questionTypeSelectBox = $(panelBody).find("#question-type-" + questionNumber);
	var questionType = questionTypeSelectBox.val();
	var numberOfChoices = choicesNumberSelectBox.val();

	if (questionType == "Best First") {
		var lowestRank = 1;
		choicesPanel.remove();
		panelBody.append(createChoicesPanel(lowestRank, numberOfChoices, panelBody));
		setNoAnswerOnChange();
	} else if (questionType == "Best In The Middle") {
		var lowestRank = (parseInt(numberOfChoices) + 1) / -2;
		choicesPanel.remove();
		panelBody.append(createChoicesPanel(lowestRank, numberOfChoices, panelBody));
		setNoAnswerOnChange();
	}
}

/**
 * Draws the panel body of a text question
 * 
 * @param panelBody
 */
function drawTextQuestion(panelBody) {
	var formGroups = '<div class="form-group"> \
		<label for="question-type-%i" class="col-sm-3 control-label">Fragetyp</label> \
		<div class="col-sm-9"> \
			<select id="question-type-%i" name="question-type-%i" class="form-control" \
				required="required"> \
				<option selected="selected">Textfrage</option> \
				<option>Best First</option> \
				<option>Best In The Middle</option> \
			</select> \
		</div> \
	</div> \
	<div class="form-group"> \
		<label for="question-%i" class="col-sm-3 control-label">Frage</label> \
		<div class="col-sm-9"> \
			<textarea class="form-control" rows="3" id="question-%i" name="question-%i" \
				required="required"></textarea> \
		</div> \
	</div> \
	<div class="form-group"> \
		<label for="max-chars-%i" class="col-sm-3 control-label">Max Zeichen</label> \
		<div class="col-sm-9"> \
			<input type="number" class="form-control" id="max-chars-%i" name="max-chars-%i" \
				min="2" max="1000" value="1000" required="required" /> \
		</div> \
	</div> \
	<div class="form-group"> \
		<label for="numbers-only-%i" class="col-sm-3 control-label">Nur Zahlen</label> \
		<div class="col-sm-9"> \
			<input type="checkbox" value="" id="numbers-only-%i" name="numbers-only-%i" /> \
		</div> \
	</div>';

	var questionNumber = panelBody.attr('id').split("-").pop();
	formGroups = formGroups.replace(/%i/g, questionNumber);

	panelBody.empty();
	panelBody.append(formGroups);

	setSelectOnChange();
}

/**
 * Draws the panel body of a best first question
 * 
 * @param panelBody
 */
function drawBestFirst(panelBody) {
	var formGroups = '<div class="form-group"> \
		<label for="question-type-%i" class="col-sm-3 control-label">Fragetyp</label> \
		<div class="col-sm-9"> \
			<select id="question-type-%i" name="question-type-%i" class="form-control" required="required"> \
				<option>Textfrage</option> \
				<option selected="selected">Best First</option> \
				<option>Best In The Middle</option> \
			</select> \
		</div> \
	</div> \
	<div class="form-group"> \
		<label for="choices-number-%i" class="col-sm-3 control-label">Anzahl Choices</label> \
		<div class="col-sm-9"> \
			<select id="choices-number-%i" name="choices-number-%i" class="form-control" required="required"> \
				<option>2</option> \
				<option>3</option> \
				<option>4</option> \
				<option selected="selected">5</option> \
				<option>6</option> \
				<option>7</option> \
			</select> \
		</div> \
	</div> \
	<div class="form-group"> \
		<label for="question-%i" class="col-sm-3 control-label">Frage</label> \
		<div class="col-sm-9"> \
			<textarea class="form-control" rows="3" id="question-%i" name="question-%i" required="required"></textarea> \
		</div> \
	</div>';

	formGroups = formGroups.concat(createChoicesPanel(1, 5, panelBody));

	var questionNumber = panelBody.attr('id').split("-").pop();
	formGroups = formGroups.replace(/%i/g, questionNumber);

	panelBody.empty();
	panelBody.append(formGroups);

	setSelectOnChange();
	setNoAnswerOnChange();
}

/**
 * Draws the panel body of a best in the middle question
 * 
 * @param panelBody
 */
function drawBestInTheMiddle(panelBody) {
	var formGroups = '<div class="form-group"> \
		<label for="question-type-%i" class="col-sm-3 control-label">Fragetyp</label> \
		<div class="col-sm-9"> \
			<select id="question-type-%i" name="question-type-%i" class="form-control" required="required"> \
				<option>Textfrage</option> \
				<option>Best First</option> \
				<option selected="selected">Best In The Middle</option> \
			</select> \
		</div> \
	</div> \
	<div class="form-group"> \
		<label for="choices-number-%i" class="col-sm-3 control-label">Anzahl Choices</label> \
		<div class="col-sm-9"> \
			<select id="choices-number-%i" name="choices-number-%i" class="form-control" required="required"> \
				<option>3</option> \
				<option selected="selected">5</option> \
				<option>7</option> \
			</select> \
		</div> \
	</div> \
	<div class="form-group"> \
		<label for="question-%i" class="col-sm-3 control-label">Frage</label> \
		<div class="col-sm-9"> \
			<textarea class="form-control" rows="3" id="question-%i" name="question-%i" required="required"></textarea> \
		</div> \
	</div>';

	formGroups = formGroups.concat(createChoicesPanel(-3, 5, panelBody));

	var questionNumber = panelBody.attr('id').split("-").pop();
	formGroups = formGroups.replace(/%i/g, questionNumber);

	panelBody.empty();
	panelBody.append(formGroups);

	setSelectOnChange();
	setNoAnswerOnChange();
}

/**
 * Creates the choices panel for a multiple choice question
 * 
 * @param lowestRank
 * @param numberOfChoices
 * @returns {String}
 */
function createChoicesPanel(lowestRank, numberOfChoices, panelBody) {
	var choices = '<!-- Choices --> \
		<div class="panel panel-thb"> \
			<div class="panel-heading panel-heading-thb"> \
				<h6 class="panel-title">Choices</h6> \
			</div> \
			<div class="panel-body"> \
				%formGroups \
			</div> \
		</div>';

	var formGroups = '';

	if (lowestRank > 0) {
		formGroups = createChoicesBestFist(lowestRank, numberOfChoices);
	} else if (lowestRank < 0) {
		formGroups = createChoicesBestInTheMiddle(lowestRank, numberOfChoices);
	}

	var questionNumber = panelBody.attr('id').split("-").pop();
	formGroups = formGroups.replace(/%i/g, questionNumber);

	choices = choices.replace("%formGroups", formGroups);

	return choices;
}

/**
 * Creates the choices divs for a best first question
 * 
 * @param lowestRank
 * @param numberOfChoices
 * @returns {String}
 */
function createChoicesBestFist(lowestRank, numberOfChoices) {
	var formGroups = '';

	// create choices except n/a choice
	for (choiceNumber = 1; choiceNumber <= numberOfChoices; choiceNumber++) {
		var formGroup = '<div class="form-group"> \
			<label for="choice-text-%i-%choiceNumber" class="col-sm-1 control-label">%choiceNumber</label> \
			<div class="col-sm-11"> \
				<input type="text" class="form-control" id="choice-text-%i-%choiceNumber" name="choice-text-%i-%choiceNumber" \
					required="required" placeholder="Text" /> \
				<input type="hidden" name="choice-grade-%i-%choiceNumber" value="%grade"/> \
			</div> \
		</div>';

		formGroup = formGroup.replace(/%choiceNumber/g, choiceNumber);
		formGroup = formGroup.replace(/%grade/g, choiceNumber);
		formGroups = formGroups.concat(formGroup);
	}

	// add n/a choice
	formGroups = formGroups.concat(createNoAnswerOption());

	return formGroups;
}

/**
 * Creates the choices divs for a best in the middle question
 * 
 * @param lowestRank
 * @param numberOfChoices
 * @returns {String}
 */
function createChoicesBestInTheMiddle(lowestRank, numberOfChoices) {
	var formGroups = '';

	// create choices except n/a choice
	var highestRank = lowestRank * -1;
	var grade = 1;
	for (choiceNumber = highestRank; choiceNumber >= lowestRank; choiceNumber--) {
		// always skip grade -1 and 0
		if (choiceNumber == -1 || choiceNumber == 0) {
			continue;
		}

		var formGroup = '<div class="form-group"> \
			<label for="choice-text-%i-%grade" class="col-sm-1 control-label">%choiceNumber</label> \
			<div class="col-sm-11"> \
				<input type="text" class="form-control" id="choice-text-%i-%grade" name="choice-text-%i-%grade" \
					required="required" placeholder="Text" /> \
				<input type="hidden" name="choice-grade-%i-%grade" value="%choiceNumber"/> \
			</div> \
		</div>';

		formGroup = formGroup.replace(/%choiceNumber/g, choiceNumber);
		formGroup = formGroup.replace(/%grade/g, grade);
		formGroups = formGroups.concat(formGroup);

		grade++;
	}

	// add n/a choice
	formGroups = formGroups.concat(createNoAnswerOption());

	return formGroups;
}

/**
 * Creates the checkbox for toggling the n/a option with the accompanying text field
 * 
 * @returns {String}
 */
function createNoAnswerOption() {
	return '<div class="form-group">\
		<label class="col-sm-1 control-label"></label> \
	    <div class="col-sm-11">\
		    <div class="checkbox">\
		      <label>\
		        <input type="checkbox" class="allow-no-answer-checkbox" name="no-answer-%i">"keine Angabe" erlauben\
		      </label>\
		    </div>\
	  </div>\
	</div>\
	<div class="form-group hidden allow-no-answer-div"> \
		<label for="choice-text-%i-0" class="col-sm-1 control-label"></label> \
		<div class="col-sm-11"> \
			<input type="text" class="form-control" id="choice-text-%i-0" name="choice-text-%i-0" placeholder="Text"/>\
		</div> \
	</div>';
}

/**
 * Enables the toggling of the text field of the n/a checkbox
 */
function setNoAnswerOnChange() {
	$(".allow-no-answer-checkbox").change(function() {
		var checkBox = $(this);
		var panelBody = checkBox.closest(".panel-body");
		var allowNoChoiceDiv = panelBody.find(".allow-no-answer-div");
		var textBox = allowNoChoiceDiv.find(".form-control");

		if (checkBox.is(":checked")) {
			allowNoChoiceDiv.removeClass('hidden');
			textBox.attr('required', 'required');
		} else {
			allowNoChoiceDiv.addClass('hidden');
			textBox.removeAttr('required');
			textBox.val("");
		}
	});
}

/**
 * Enables the insertion of a new question below the last question
 */
function setNewQuestionOnClick() {
	$("#new-question")
			.click(
					function() {
						var newQuestionPanelHtml = '<div id="question-panel-%i" class="panel panel-thb"> \
							<div class="panel-heading panel-heading-thb"> \
								<h3 class="panel-title">Frage %i \
									<span class="glyphicon glyphicon-remove pull-right" aria-hidden="true" style="cursor: pointer;" \
										title="Frage l&ouml;schen" data-toggle="modal" data-target="#delete-dialog"></span> \
								</h3> \
							</div> \
							<div id="question-panel-body-%i" class="panel-body"></div> \
						</div>';

						// increment question count
						var questionCountField = $("#question-count");
						var questionCount = parseInt(questionCountField.val()) + 1;
						questionCountField.val(questionCount.toString());

						// create new question panel
						var newQuestionButton = $(this);
						var questionPanelAbove = newQuestionButton.parent().prev();
						newQuestionPanelHtml = newQuestionPanelHtml.replace(/%i/g, questionCount);
						questionPanelAbove.after(newQuestionPanelHtml);

						// fill question panel body
						var newQuestionPanel = newQuestionButton.parent().prev();
						var questionPanelBody = newQuestionPanel.find('.panel-body');
						drawTextQuestion(questionPanelBody);
					});
}

/**
 * Removes the question panel with id = panelId and adjusts the attribute values of subsequent question panels
 * 
 * @param panelId
 */
function removeQuestionPanel(panelId) {
	// decrement question count
	var questionCountField = $("#question-count");
	var questionCount = parseInt(questionCountField.val()) - 1;
	questionCountField.val(questionCount.toString());

	// increment attribute values in subsequent question panels
	var questionPanelToDelete = $("#" + panelId);
	questionPanelToDelete.nextAll("div[id|='question-panel']").each(function(counter, element) {
		var questionPanel = $(element);
		var questionNumberOld = parseInt(questionPanel.attr('id').split("-").pop());
		var questionNumberNew = questionNumberOld - 1;

		questionPanel.attr('id', 'question-panel-' + questionNumberNew);
		questionPanel.find('h3').html(questionPanel.find('h3').html().replace('Frage ' + questionNumberOld, 'Frage ' + questionNumberNew));
		questionPanel.find('#question-panel-body-' + questionNumberOld).attr('id', 'question-panel-body-' + questionNumberNew);
		var attributeNames = ["question-type-", "question-", "max-chars-", "numbers-only-", "choices-number-"];
		for (i = 0; i < attributeNames.length; i++) {
			questionPanel.find("label[for='" + attributeNames[i] + questionNumberOld + "']").attr('for', attributeNames[i] + questionNumberNew);
			questionPanel.find("#" + attributeNames[i] + questionNumberOld).attr({
				'id' : attributeNames[i] + questionNumberNew,
				'name' : attributeNames[i] + questionNumberNew
			});
		}
		questionPanel.find("label[for|='choice-text-" + questionNumberOld + "']").each(function(counter, element) {
			var label = $(element);
			var labelFor = label.attr('for');
			label.attr('for', labelFor.replace('choice-text-' + questionNumberOld, 'choice-text-' + questionNumberNew));
		});
		questionPanel.find("input[id|='choice-text-" + questionNumberOld + "']").each(function(counter, element) {
			var input = $(element);
			var inputIdOld = input.attr('id');
			var inputIdNew = inputIdOld.replace('choice-text-' + questionNumberOld, 'choice-text-' + questionNumberNew);
			input.attr({
				'id' : inputIdNew,
				'name' : inputIdNew
			});
		});
		questionPanel.find("input[name|='choice-grade-" + questionNumberOld + "']").each(function(counter, element) {
			var input = $(element);
			var inputNameOld = input.attr('name');
			var inputNameNew = inputNameOld.replace('choice-grade-' + questionNumberOld, 'choice-grade-' + questionNumberNew);
			input.attr('name', inputNameNew);
		});
		questionPanel.find("input[name='no-answer-" + questionNumberOld + "']").attr('name', 'no-answer-' + questionNumberNew);
	});

	// remove question panel and hide dialog
	questionPanelToDelete.remove();
	$("#delete-dialog").modal('hide');
}

/**
 * Displays the modal dialog when the delete button of a question panel is clicked and sets the behavior of the affirmative button. 
 * The affirmative button causes the removal of the question panel.
 */
function setModalOnShow() {
	$('#delete-dialog').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var panelId = button.closest('.panel').attr('id');
		var deleteMethod = "removeQuestionPanel(" + "'" + panelId + "'" + ")";
		var modal = $(this);
		modal.find('#delete-button').attr("onclick", deleteMethod);
	});
}