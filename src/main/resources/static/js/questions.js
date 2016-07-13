$(document).ready(function() {
	setSelectOnChange();
});

/**
 * Toggles the omit question functionality
 */
$(document).on("click", ".omit-question", function(event) {
	var omitQuestionCheckBox = $(this);

	var panel = omitQuestionCheckBox.closest(".panel");
	var panelBody = panel.find(".panel-body");
	var formInputs = panelBody.find("input:not(:checkbox), textarea, select");

	if (omitQuestionCheckBox.is(":checked")) {
		formInputs.removeAttr("required");
	} else {
		formInputs.attr("required", "required");
	}
});

/**
 * Sets the onChange behavior of the select boxes "question type" and "number of choices"
 */
function setSelectOnChange() {
	$("select").change(function() {
		var selectBox = $(this);

		if (selectBox.attr('id').match(/question-type-\d+/)) {
			var panelBody = selectBox.closest(".panel-body");
			replaceQuestionType(selectBox, panelBody);
		} else if (selectBox.attr('id').match(/choices-number-\d+/)) {
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
 * Redraws the choices of a multiple choice question depending on the selected number of choices and question type
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
		panelBody.append(createChoicesPanel(lowestRank, numberOfChoices));
	} else if (questionType == "Best In The Middle") {
		var lowestRank = (parseInt(numberOfChoices) + 1) / -2;
		choicesPanel.remove();
		panelBody.append(createChoicesPanel(lowestRank, numberOfChoices));
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

	formGroups = formGroups.concat(createChoicesPanel(1, 5));

	var questionNumber = panelBody.attr('id').split("-").pop();
	formGroups = formGroups.replace(/%i/g, questionNumber);

	panelBody.empty();
	panelBody.append(formGroups);

	setSelectOnChange();
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

	formGroups = formGroups.concat(createChoicesPanel(-3, 5));

	var questionNumber = panelBody.attr('id').split("-").pop();
	formGroups = formGroups.replace(/%i/g, questionNumber);

	panelBody.empty();
	panelBody.append(formGroups);

	setSelectOnChange();
}

/**
 * Creates the choices panel for a multiple choice question
 * 
 * @param lowestRank
 * @param numberOfChoices
 * @returns {String}
 */
function createChoicesPanel(lowestRank, numberOfChoices) {
	var choices = '<!-- Choices --> \
		<div class="panel panel-danger"> \
			<div class="panel-heading"> \
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

	for (choiceNumber = lowestRank; choiceNumber <= numberOfChoices; choiceNumber++) {
		var formGroup = '<div class="form-group"> \
			<label for="choice-%i-%choiceNumber" class="col-sm-1 control-label">%choiceNumber</label> \
			<div class="col-sm-11"> \
				<input type="text" class="form-control" id="choice-%i-%choiceNumber" name="choice-%i-%choiceNumber" \
					required="required" placeholder="Text" /> \
			</div> \
		</div>';

		formGroup = formGroup.replace(/%choiceNumber/g, choiceNumber);
		formGroups = formGroups.concat(formGroup);
	}

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

	var highestRank = lowestRank * -1;
	for (choiceNumber = highestRank; choiceNumber >= lowestRank; choiceNumber--) {
		// always skip grade -1 and 0
		if (choiceNumber == -1 || choiceNumber == 0){
			continue;
		}
		
		var formGroup = '<div class="form-group"> \
			<label for="choice-%i-%choiceNumber" class="col-sm-1 control-label">%choiceNumber</label> \
			<div class="col-sm-11"> \
				<input type="text" class="form-control" id="choice-%i-%choiceNumber" name="choice-%i-%choiceNumber" \
					required="required" placeholder="Text" /> \
			</div> \
		</div>';

		formGroup = formGroup.replace(/%choiceNumber/g, choiceNumber);
		formGroups = formGroups.concat(formGroup);
	}

	return formGroups;
}