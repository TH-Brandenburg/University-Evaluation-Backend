package de.thb.ue.backend.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DSLParser {

	private int answers_quantity = 0;
	private int index_q = 0;
	private int index_a = 0;
	private boolean equal_q_a = true;
	private int line_index = 0;
	private final Path FilePath;
	private final static Charset ENCODING = StandardCharsets.UTF_8;

	private ArrayList<String> data = new ArrayList<String>();
	private static ArrayList<ArrayList<String>> data_out = new ArrayList<ArrayList<String>>();
	// type;answersquantity;line;error;questiontext; grade;answertext;error

	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject));
	}

	/*
	 * private String quote(String aText){ String QUOTE = "'"; return QUOTE +
	 * aText + QUOTE; }
	 */

	public static void main(String[] args) throws IOException {
		// ReadWithScanner parser = new ReadWithScanner("test.txt");
		// parser.processLineByLine();
		// CreateXML(data_out);
		// log("\nDone.");
	}

	public DSLParser(String FileName) {
		FilePath = Paths.get(FileName);
	}

	public final void processLineByLine() throws IOException {
		try (Scanner scanner = new Scanner(FilePath, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine().replace("\uFEFF", ""); // UTF
																			// BOM
																			// fix
				processLine(nextLine, line_index);
				line_index++;
			}
		}
	}

	protected void processLine(String Line, int line_error) {
		Scanner scanner = new Scanner(Line);
		scanner.useDelimiter(":");
		try {
			while (scanner.hasNext()) {
				if ((line_index + 1) % 2 != 0) {
					data.clear();
					for (int i = 0; i < 4; i++)
						data.add(null);
				}
				String q_a = scanner.next();
				String q_a_details = scanner.next();
				if (q_a.equals(Character.toString('Q'))) {
					index_q++;
					// log(quote(q_a.trim()) + quote(q_a_details.trim()));
					data.add(4, q_a_details);
					data.add(3, "No");
					data.remove(4);
				} else if (q_a.equals(Character.toString('A')) && index_q - index_a == 1) {
					index_a++;
					// log(quote(q_a.trim()) + quote(q_a_details.trim()));
					data.add(3, "No");
					data.remove(4);
					Scanner scanner_answers = new Scanner(q_a_details);
					scanner_answers.useDelimiter(";");
					answers_quantity = 1;
					try {
						while (scanner_answers.hasNext()) {
							String answer_next = scanner_answers.next();
							try {
								if (answer_next.indexOf('-') == 1 || answer_next.indexOf('-') == 2) {
									data.add(0, "Closed");
									data.remove(1);
									String[] answer_split = answer_next.split("-");
									String grade = answer_split[0];
									String answer = answer_split[1];
									grade = grade.replaceAll("\\s", "");
									int grade_int;
									if (grade.isEmpty() || !grade.matches("[0-5]")) {
										grade_int = -1;
									} else {
										grade_int = Integer.parseInt(grade);
									}
									// log(grade);
									if (grade_int >= 0 && grade_int < 6) {
										data.add(answers_quantity * 3 + 2, Integer.toString(grade_int));
										if (answer.length() > 0) {
											data.add(answers_quantity * 3 + 3, answer);
											data.add(answers_quantity * 3 + 4, "No");
											data.add(1, Integer.toString(answers_quantity));
											data.remove(2);
										} else {
											data.add(answers_quantity * 3 + 3, null);
											data.add(answers_quantity * 3 + 4, "Answer is not defined!");
										}
									} else {
										data.add(answers_quantity * 3 + 2, null);
										data.add(answers_quantity * 3 + 3, null);
										data.add(answers_quantity * 3 + 4, "Grade isn't in range 0-5!");
									}
								} else if ((answer_next.indexOf('-') != 1 || answer_next.indexOf('-') != 2)
										&& (answer_next.indexOf('-') == 4 || answer_next.indexOf('-') == 5)) {
									String[] answer_split = answer_next.split("-");
									String type = answer_split[0];
									type = type.replaceAll("\\s", "");
									String symbols = answer_split[1];
									if (type.equals("Open") && symbols.matches("[0-9]{3}")) {
										int symbols_int = Integer.parseInt(symbols);
										data.add(0, type);
										data.add(1, "0");
										data.remove(2);
										data.remove(3);
										data.add(answers_quantity * 3 + 2, "0");
										data.add(answers_quantity * 3 + 3, "");
										if (symbols_int < 1000) {
											data.add(answers_quantity * 3 + 4, Integer.toString(symbols_int));
										} else {
											data.add(answers_quantity * 3 + 4, "Too long answer!");
										}
									} else {
										data.add(answers_quantity * 3 + 2, null);
										data.add(answers_quantity * 3 + 3, null);
										data.add(answers_quantity * 3 + 4, "Unrecognised answer!");
									}
								} else {
									data.add(answers_quantity * 3 + 2, null);
									data.add(answers_quantity * 3 + 3, null);
									data.add(answers_quantity * 3 + 4, "Cant find '-'!(unrecognised answer)");
								}
							} catch (Exception e) {
								log(e);
							} finally {
								answers_quantity++;
							}
						}
					} catch (Exception e) {
						log(e);
					} finally {
						scanner_answers.close();
					}
				} else if (q_a.equals(Character.toString('A')) && index_q - index_a != 1) {
					index_a++;
					data.add(3, "Wrong order!");
					data.remove(4);
					// log(quote(q_a.trim()) + quote(q_a_details.trim()));
				} else {
					if (index_q <= index_a && (line_index + 1) % 2 != 0) {
						data.add(3, "Question is not defined!");
						if (data.size() > 5) {
							data.remove(4);
						}
					} else if (index_q >= index_a && (line_index + 1) % 2 == 0) {
						data.add(3, "Answer is not defined!");
						if (data.size() > 5) {
							data.remove(4);
						}
					}
					return;
				}
				if ((line_index + 1) % 2 == 0 && index_q - index_a != 0) {
					equal_q_a = false;
				}
			}
		} catch (Exception e) {
			log(e + "\nError in string: " + line_error + " - " + Line);
		} finally {
			scanner.close();

			if ((line_index + 1) % 2 == 0) {
				data.add(2, Integer.toString(line_index));
				data.remove(3);
				ArrayList<String> buffer = new ArrayList<String>(data);
				data_out.add(buffer);
				log(data_out);
			}
		}
	}

	public static void CreateXML(ArrayList<ArrayList<String>> dataxml) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element mainRootElement = doc.createElement("Questionary");
			doc.appendChild(mainRootElement);

			// append child elements to root element
			for (int i = 0; i < dataxml.size(); i++) {
				int answers_quantity = 0;
				String type = dataxml.get(i).get(0);
				String answersquantity = dataxml.get(i).get(1);
				String line = dataxml.get(i).get(2);
				String error = dataxml.get(i).get(3);
				String questiontext = dataxml.get(i).get(4);
				if (!answersquantity.isEmpty()) {
					answers_quantity = Integer.parseInt(answersquantity);
				}
				if (type == null) {
					type = "";
				}
				if (questiontext == null) {
					questiontext = "";
				}

				mainRootElement.appendChild(Question(dataxml.get(i), answers_quantity, doc, type, answersquantity, line, error, questiontext));
			}

			// output DOM XML to console
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("file.xml"));
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node Question(ArrayList xml_list, int count, Document doc, String type, String ans_quent, String line, String error, String q_text) {
		Element question = doc.createElement("Question");
		question.setAttribute("type", type);
		question.setAttribute("answersquantity", ans_quent);
		question.setAttribute("line", line);
		question.setAttribute("error", error);
		question.setAttribute("questiontext", q_text);

		if (type.equals("Closed") && count != 0) {
			for (int i = 0; i < count; i++) {
				String grade = (String) xml_list.get(5 + 3 * i);
				String answertext = (String) xml_list.get(6 + 3 * i);
				String error_answer = (String) xml_list.get(7 + 3 * i);
				question.appendChild(Answers_closed(doc, question, grade, answertext, error_answer));
			}
		} else if (type.equals("Open") && count == 0) {
			String grade = (String) xml_list.get(5);
			String answertext = (String) xml_list.get(6);
			String symbols = (String) xml_list.get(7);
			question.appendChild(Answers_open(doc, question, grade, answertext, symbols));
		} else {
			question.appendChild(Answers_closed(doc, question, "", "", ""));
		}

		return question;
	}

	private static Node Answers_closed(Document doc, Element element, String grade, String answertext, String error) {
		Element answer = doc.createElement("Answer");
		answer.setAttribute("grade", grade);
		answer.setAttribute("answer_text", answertext);
		answer.setAttribute("error", error);
		return answer;
	}

	private static Node Answers_open(Document doc, Element element, String grade, String answertext, String symbols) {
		Element answer = doc.createElement("Answer");
		answer.setAttribute("grade", grade);
		answer.setAttribute("answer_text", answertext);
		answer.setAttribute("symbolsquantity", symbols);
		return answer;
	}
}