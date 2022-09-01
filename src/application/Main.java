package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.scene.effect.Glow;
import javafx.scene.effect.MotionBlur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Main extends Application {

	private Konekcija konekcija=new Konekcija();
	private int ocjena;
	private int[]ocjene=new int[4];
	private double xOffset = 0;
	private double yOffset = 0;
	private int oc;
	
	public void start(Stage primaryStage) throws Exception {
		
		//konekcija = new Konekcija();
		VBox root = new VBox(33);
		root.setPadding(new Insets(25, 30, 100, 30));   //g, de, d, li

		HBox top = new HBox(170);
		Label naslov = new Label("User login");
		naslov.setTextFill(Color.WHITE);
		naslov.setFont(new Font("Arial", 30));
		Button close = new Button("Exit");
		close.setEffect(new Reflection());
		close.setOnAction( e -> primaryStage.close() );
		close.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		close.setMaxHeight(30);
		close.setMinHeight(30);
		close.setPrefHeight(Region.USE_COMPUTED_SIZE);
		close.setMaxWidth(Double.MAX_VALUE);
		close.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							close.setEffect(new Glow());
						}
		});
		close.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            close.setEffect(new Reflection());
			          }
		});
		top.setAlignment(Pos.CENTER);
		top.getChildren().addAll(naslov, close);
		
		Separator separator = new Separator();
		separator.setStyle("-fx-background-color: indigo;");
		separator.setOpacity(0.5);
		separator.setPadding(new Insets(0, 0, 10, 0));
		
		VBox unos = new VBox(15);
		TextField unos1 = new TextField("");
		unos1.setPromptText("Username");
		CornerRadii corn = new CornerRadii(15);
		Background background = new Background(new BackgroundFill(Color.BLACK, corn, Insets.EMPTY));
		unos1.setBackground(background);
		unos1.setStyle("-fx-text-fill: white;");
		unos1.setMaxHeight(40);
		unos1.setMinHeight(40);
		unos1.setPrefHeight(Region.USE_COMPUTED_SIZE);
		PasswordField unos2 = new PasswordField();
		unos2.setPromptText("Password");
		unos2.setBackground(background);
		unos2.setStyle("-fx-text-fill: white;");
		unos2.setMaxHeight(40);
		unos2.setMinHeight(40);
		unos2.setPrefHeight(Region.USE_COMPUTED_SIZE);
		CheckBox checkBox = new CheckBox("Show password");
		checkBox.setStyle("-fx-text-fill: white;");
		TextField textField = new TextField();
		textField.setBackground(background);
		textField.setStyle("-fx-text-fill: white;");
		textField.setPromptText("Password");
		textField.setMaxHeight(40);
		textField.setMinHeight(40);
		textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
		textField.setManaged(false);
		textField.setVisible(false);
		textField.managedProperty().bind(checkBox.selectedProperty());
		textField.visibleProperty().bind(checkBox.selectedProperty());
		unos2.managedProperty().bind(checkBox.selectedProperty().not());
		unos2.visibleProperty().bind(checkBox.selectedProperty().not());
		textField.textProperty().bindBidirectional(unos2.textProperty());
		unos.getChildren().addAll(unos1, unos2, textField, checkBox);
		
		Label greska = new Label("");
		greska.setStyle("-fx-text-fill: blueviolet;");

		Button potvrdi = new Button("Login");
		potvrdi.setEffect(new Reflection());
		potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		potvrdi.setMaxHeight(33);
		potvrdi.setMinHeight(33);
		potvrdi.setPrefHeight(Region.USE_COMPUTED_SIZE);
		potvrdi.setMaxWidth(Double.MAX_VALUE);
		potvrdi.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							potvrdi.setEffect(new Glow());
						}
		});
		potvrdi.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            potvrdi.setEffect(new Reflection());
			          }
		});
		
		Separator separator2 = new Separator();
		separator2.setStyle("-fx-background-color: indigo;");
		separator2.setOpacity(0.5);
		separator2.setPadding(new Insets(0, 0, 10, 0));
		
		Label zahvalnica = new Label("Thank you for using electronic diary and being up-to-date. ");
		zahvalnica.setStyle("-fx-text-fill: blueviolet;");
		
		root.getChildren().addAll(top, separator, unos, greska, potvrdi, separator2, zahvalnica);
		root.setStyle("-fx-background-color: rgb(45,33,70);");

		potvrdi.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String korisnickoIme = unos1.getText().trim();
				String sifra = unos2.getText().trim();
				try {
					if (LoginKontroler.Login(korisnickoIme, sifra, konekcija)) {
						if(Ucenik.getUcenik(korisnickoIme) != null) {
							ucenikScena(korisnickoIme);
							primaryStage.hide();
						}
						else if(Profesor.getProfesor(korisnickoIme) != null){
							profesorScena(korisnickoIme);
							primaryStage.hide();
						}
					}
					else {
						greska.setText("Wrong input. Please try again. ");
					}
				} catch (Exception e) {
					//System.err.println("Greska kod validacije");
					e.printStackTrace();
				}
			}
		});
		
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
			
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			}
			
		});
		Scene scene = new Scene(root, 400, 500);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
	//	scene.setFill(Color.TRANSPARENT);
		//primaryStage.setTitle("Elektronski dnevnik");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	//------------------------------------------------------------------ucenik scena-------------------------------------------------------------------
	private void ucenikScena(String korisnickoIme) throws FileNotFoundException {
		
		Ucenik u = Ucenik.getUcenik(korisnickoIme);
		Stage stage2 = new Stage();
		VBox root = new VBox(35);
		root.setPadding(new Insets(25, 50, 100, 30));
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(root);
	    scrollPane.setPannable(true);
	    scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS); 
	    scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	    scrollPane.setStyle("-fx-background-color: rgb(45,33,70);");
		
	    Scene ucenik = new Scene(scrollPane, 500, 500);

		HBox top = new HBox(170);
		Label naslov = new Label("Welcome to student diary ");
		naslov.setTextFill(Color.WHITE);
		naslov.setFont(new Font("Arial", 20));
		Button close = new Button("Exit");
		close.setEffect(new Reflection());
		close.setOnAction( e -> stage2.close() );
		close.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		close.setMaxHeight(30);
		close.setMinHeight(30);
		close.setPrefHeight(Region.USE_COMPUTED_SIZE);
		close.setMaxWidth(Double.MAX_VALUE);
		close.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							close.setEffect(new Glow());
						}
		});
		close.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            close.setEffect(new Reflection());
			          }
		});
		top.setAlignment(Pos.CENTER);
		top.getChildren().addAll(naslov, close);
		
		Separator separator = new Separator();
		separator.setStyle("-fx-background-color: indigo;");
		separator.setOpacity(0.5);
		separator.setPadding(new Insets(0, 0, 10, 0));

		//ispis osnovnih podataka ucenika
		VBox down = new VBox(10);
		VBox ispis = new VBox(8);
		BorderPane ToolBorderPane = new BorderPane();     
		//ToolBar tBarLeft=new ToolBar();      
		//ToolBar tBarRight=new ToolBar();     
		HBox podaciH = new HBox(130);
		VBox podaciV = new VBox(8);
		podaciV.setPadding(new Insets(0, 150, 0, 0));
		Label op = new Label("Basic information");
		op.setTextFill(Color.WHITE);
		op.setMaxWidth(Double.MAX_VALUE);
		op.setAlignment(Pos.CENTER);
		op.setPadding(new Insets(0, 0, 10, 0));
		op.setFont(new Font("Arial", 16));
		HBox prvi = new HBox(5);
		Label ime = new Label("Name: ");
		ime.setTextFill(Color.WHITE);
		Label ispisImena = new Label(u.getIme());
		ispisImena.setTextFill(Color.WHITE);
		prvi.getChildren().addAll(ime, ispisImena);
		
		HBox drugi = new HBox(5);
		Label prezime = new Label("Surname: ");
		prezime.setTextFill(Color.WHITE);
		Label ispisPrezimena = new Label(u.getPrezime());
		ispisPrezimena.setTextFill(Color.WHITE);
		drugi.getChildren().addAll(prezime, ispisPrezimena);
		
		HBox treci = new HBox(5);
		Label skola = new Label("School name: ");
		skola.setTextFill(Color.WHITE);
		//if(u.getSkola()!=null) {
			Label ispisSkole = new Label(u.getSkola().getNaziv());
			ispisSkole.setTextFill(Color.WHITE);
			treci.getChildren().addAll(skola, ispisSkole);
		//}
		

		HBox cetvrti = new HBox(5);
		Label mjesto = new Label("Place: ");
		mjesto.setTextFill(Color.WHITE);
		Label ispisMjesta = new Label(u.getMjesto() + ", " + u.getGrad());
		ispisMjesta.setTextFill(Color.WHITE);
		cetvrti.getChildren().addAll(mjesto, ispisMjesta);

		HBox peti = new HBox(5);
		Label razred = new Label("Class: ");
		razred.setTextFill(Color.WHITE);
		Label ispisRazreda = new Label("" + u.getRazred());
		ispisRazreda.setTextFill(Color.WHITE);
		ispisRazreda.setPadding(new Insets(0, 0, 8, 0));
		peti.getChildren().addAll(razred, ispisRazreda);
		ImageView imageView = new ImageView();
		Image image = new Image(new FileInputStream("D:\\schoolLogo.png"));
		imageView.setImage(image);
		imageView.setFitHeight(110); 
	    imageView.setFitWidth(60); 
	    //imageView.setPreserveRatio(true);  
	    podaciV.getChildren().addAll(prvi, drugi, treci, cetvrti, peti);
		//podaciH.getChildren().addAll(podaciV, imageView);
	    ToolBorderPane.setLeft(podaciV);      
		ToolBorderPane.setRight(imageView); 
		//ispis.getChildren().addAll(separator, op, prvi, drugi, treci, cetvrti, peti);
		ispis.getChildren().add(separator);
		ispis.getChildren().addAll(op, ToolBorderPane);
		//kraj ispisa osnovnih podataka ucenika
		
		//ispis svih predmeta sa ocjenom
		VBox sesti = new VBox(10);
		VBox sedmi = new VBox(0);
		sedmi.setPadding(new Insets(5, 0, 5, 0));
		Separator separator2 = new Separator();
		separator2.setStyle("-fx-background-color: indigo;");
		separator2.setOpacity(0.5);
		separator2.setPadding(new Insets(0, 0, 10, 0));
		//separator2.setPadding(new Insets(5, 0, 0, 0));
		sesti.getChildren().add(separator2);
		Label predmeti1 = new Label("All subjects with grades");
		predmeti1.setTextFill(Color.WHITE);
		predmeti1.setMaxWidth(Double.MAX_VALUE);
		predmeti1.setAlignment(Pos.CENTER);
		predmeti1.setPadding(new Insets(0, 0, 10, 0));
		predmeti1.setFont(new Font("Arial", 16));
		//TextArea dugmici = new TextArea();
		sesti.getChildren().add(predmeti1);
		/*ArrayList<Button> listbutton = new ArrayList<Button>();
		Button button = new Button();*/
		if(u.getSveOcjene().isEmpty()) {
			Label gr = new Label("This student does not have any grades yet.");
			gr.setTextFill(Color.WHITE);
			sedmi.getChildren().add(gr);
		}
		else {
			for(Predmet p: u.getPredmetiByOcjena()) {
				Button bt = new Button(p.getNaziv());
				bt.setPrefWidth(120);
				bt.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				sesti.getChildren().add(bt);
				//Label l = new Label("");
				//bt.setOnAction( e ->  sedmi.getChildren().add(l));
				bt.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						//bt.setBorder();
						//bt.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white; -fx-border-color: white;");
						sedmi.getChildren().clear();
						BorderPane bPane = new BorderPane();
						Label ocjena = new Label("Grade");
						ocjena.setTextFill(Color.WHITE);
						ocjena.setPadding(new Insets(0, 90, 0, 90));
						Label datum = new Label("Date");
						datum.setTextFill(Color.WHITE);
						datum.setPadding(new Insets(0, 90, 0, 90));
						bPane.setLeft(ocjena);
						bPane.setCenter(datum);
						sedmi.getChildren().add(bPane);
						ArrayList<Ocjena> lista = new ArrayList<Ocjena>();
						for(Ocjena o: u.getOcjeneIzPredmeta(bt.getText()))
							lista.add(o);
						Collections.sort(lista, Ocjena.CompareOcjena);
						for(Ocjena o: lista) {
							BorderPane bp = new BorderPane();
							//bp.setStyle("-fx-border-width: 1; -fx-border-color: white");
							Label ocj = new Label(""+o.getOcjena());
							ocj.setTextFill(Color.WHITE);
							ocj.setPadding(new Insets(0, 105, 0, 105));
							ocj.setStyle("-fx-border-width: 1; -fx-border-color: white");
							Label dat = new Label(""+o.getDatum());
							dat.setTextFill(Color.WHITE);
							dat.setPadding(new Insets(0, 80, 0, 80));
							dat.setStyle("-fx-border-width: 1; -fx-border-color: white");
							bp.setLeft(ocj);
							bp.setCenter(dat);
							sedmi.getChildren().add(bp);
							}
					}
				});
				bt.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									bt.setEffect(new Glow());
								}
				});
				bt.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					          public void handle(MouseEvent e) {
					            bt.setEffect(null);
					          }
				});
			} 
			Button bt2 = new Button("All grades");
			bt2.setPrefWidth(120);
			bt2.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
			sesti.getChildren().add(bt2);
			Button zatvori1 = new Button("Hide grades");
			zatvori1.setPrefWidth(120);
			zatvori1.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
			sesti.getChildren().add(zatvori1);
			zatvori1.setOnAction(e -> sedmi.getChildren().clear());
			zatvori1.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								zatvori1.setEffect(new Glow());
							}
			});
			zatvori1.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				          public void handle(MouseEvent e) {
				            zatvori1.setEffect(null);
				          }
			});
			bt2.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					sedmi.getChildren().clear();
					ArrayList<Ocjena> lista = new ArrayList<Ocjena>();
					for(Ocjena o: u.getSveOcjene())
						lista.add(o);
					Collections.sort(lista, Ocjena.CompareOcjena);
					sedmi.getChildren().clear();
					BorderPane bPane = new BorderPane();
					Label predmet = new Label("Subject");
					predmet.setTextFill(Color.WHITE);
					predmet.setPadding(new Insets(0, 50, 0, 50));
					Label ocjena = new Label("Grade");
					ocjena.setTextFill(Color.WHITE);
					ocjena.setPadding(new Insets(0, 55, 0, 55));
					Label datum = new Label("Date");
					datum.setTextFill(Color.WHITE);
					datum.setPadding(new Insets(0, 55, 0, 50));
					bPane.setLeft(predmet);
					bPane.setCenter(ocjena);
					bPane.setRight(datum);
					sedmi.getChildren().add(bPane);
					for(Ocjena o: lista) {
						BorderPane bp = new BorderPane();
						Label pre = new Label(""+o.getPredmetUSkoli().getPredmet().getNaziv());
						pre.setTextFill(Color.WHITE);
						pre.setPadding(new Insets(0, 30, 0, 30));
						pre.setPrefWidth(150);
						pre.setStyle("-fx-border-width: 1; -fx-border-color: white");
						Label ocj = new Label(""+o.getOcjena());
						ocj.setTextFill(Color.WHITE);
						ocj.setPadding(new Insets(0, 70, 0, 70));
						ocj.setStyle("-fx-border-width: 1; -fx-border-color: white");
						Label dat = new Label(""+o.getDatum());
						dat.setTextFill(Color.WHITE);
						dat.setPadding(new Insets(0, 40, 0, 40));
						dat.setStyle("-fx-border-width: 1; -fx-border-color: white");
						bp.setLeft(pre);
						bp.setCenter(ocj);
						bp.setRight(dat);
						sedmi.getChildren().add(bp);
					}
				}
			});
			bt2.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								bt2.setEffect(new Glow());
							}
			});
			bt2.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				          public void handle(MouseEvent e) {
				            bt2.setEffect(null);
				          }
			});
		}
		//kraj ispisa svih predmeta sa ocjenom
		
		//ispis svih predmeta sa izostankom
		VBox osmi = new VBox(10);
		VBox deveti = new VBox(0);
		deveti.setPadding(new Insets(5, 0, 5, 0));
		Separator separator3 = new Separator();
		separator3.setStyle("-fx-background-color: indigo;");
		separator3.setOpacity(0.5);
		separator3.setPadding(new Insets(0, 0, 10, 0));
		osmi.getChildren().add(separator3);
		Label predmeti2 = new Label("All subjects with absence");
		predmeti2.setTextFill(Color.WHITE);
		predmeti2.setMaxWidth(Double.MAX_VALUE);
		predmeti2.setAlignment(Pos.CENTER);
		predmeti2.setPadding(new Insets(0, 0, 10, 0));
		predmeti2.setFont(new Font("Arial", 16));
		osmi.getChildren().add(predmeti2);
		if(u.getSviIzostanci().isEmpty()) {
			Label gr = new Label("This student does not have any absences yet.");
			gr.setTextFill(Color.WHITE);
			deveti.getChildren().add(gr);
		}
		else {
			for(Predmet p: u.getPredmetiByIzostanci()) {
				Button bt = new Button(p.getNaziv());
				bt.setPrefWidth(120);
				bt.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				osmi.getChildren().add(bt);
				bt.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						deveti.getChildren().clear();
						BorderPane bPane = new BorderPane();
						Label predmet = new Label("Subject");
						predmet.setTextFill(Color.WHITE);
						predmet.setPadding(new Insets(0, 90, 0, 90));
						Label datum = new Label("Date");
						datum.setTextFill(Color.WHITE);
						datum.setPadding(new Insets(0, 90, 0, 90));
						bPane.setLeft(predmet);						
						bPane.setRight(datum);
						deveti.getChildren().add(bPane);
						for(Izostanci i: u.getIzostanciIzPredmeta(bt.getText())) {
							BorderPane bp = new BorderPane();
							Label pre = new Label(""+i.getPredmetUSkoli().getPredmet().getNaziv());
							pre.setTextFill(Color.WHITE);
							pre.setPadding(new Insets(0, 70, 0, 70));
							pre.setPrefWidth(230);
							pre.setStyle("-fx-border-width: 1; -fx-border-color: white");
							Label dat = new Label(""+i.getDatum());
							dat.setTextFill(Color.WHITE);
							dat.setPadding(new Insets(0, 75, 0, 75));
							dat.setStyle("-fx-border-width: 1; -fx-border-color: white");
							bp.setLeft(pre);
							bp.setRight(dat);
							deveti.getChildren().add(bp);
						}
					}
				});
				bt.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									bt.setEffect(new Glow());
								}
				});
				bt.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					          public void handle(MouseEvent e) {
					            bt.setEffect(null);
					          }
				});
			}
			Button bt3 = new Button("All absences");
			bt3.setPrefWidth(120);
			bt3.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
			bt3.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					deveti.getChildren().clear();
					BorderPane bPane = new BorderPane();
					Label predmet = new Label("Subject");
					predmet.setTextFill(Color.WHITE);
					predmet.setPadding(new Insets(0, 90, 0, 90));
					Label datum = new Label("Date");
					datum.setTextFill(Color.WHITE);
					datum.setPadding(new Insets(0, 90, 0, 90));
					bPane.setLeft(predmet);						
					bPane.setRight(datum);
					deveti.getChildren().add(bPane);
					for(Izostanci i: u.getSviIzostanci()) {
						BorderPane bp = new BorderPane();
						Label pre = new Label(""+i.getPredmetUSkoli().getPredmet().getNaziv());
						pre.setTextFill(Color.WHITE);
						pre.setPadding(new Insets(0, 70, 0, 70));
						pre.setPrefWidth(230);
						pre.setStyle("-fx-border-width: 1; -fx-border-color: white");
						Label dat = new Label(""+i.getDatum());
						dat.setTextFill(Color.WHITE);
						dat.setPadding(new Insets(0, 75, 0, 75));
						dat.setStyle("-fx-border-width: 1; -fx-border-color: white");
						bp.setLeft(pre);
						bp.setRight(dat);
						deveti.getChildren().add(bp);
					}
				}
			});
			bt3.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								bt3.setEffect(new Glow());
							}
			});
			bt3.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				          public void handle(MouseEvent e) {
				            bt3.setEffect(null);
				          }
			});
			osmi.getChildren().add(bt3);
			Button zatvori2 = new Button("Hide absences");
			zatvori2.setPrefWidth(120);
			zatvori2.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
			osmi.getChildren().add(zatvori2);
			zatvori2.setOnAction(e -> deveti.getChildren().clear());
			zatvori2.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								zatvori2.setEffect(new Glow());
							}
			});
			zatvori2.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				          public void handle(MouseEvent e) {
				            zatvori2.setEffect(null);
				          }
			});
		}
		//kraj ispisa svih predmeta sa izostankom
		
		VBox deseti = new VBox(10);     //spisak predmeta i sakrij predmete dugme
		VBox jedanaesti = new VBox(10);    //pojedinacno ocjenjivanje predmeta i prikaz ocjena
		Separator separator4 = new Separator();
		separator4.setStyle("-fx-background-color: indigo;");
		separator4.setOpacity(0.5);
		separator4.setPadding(new Insets(0, 0, 10, 0));
		deseti.getChildren().add(separator4);
		Label l = new Label("Grading professors (grading is anonymous)");
		l.setTextFill(Color.WHITE);
		l.setMaxWidth(Double.MAX_VALUE);
		l.setAlignment(Pos.CENTER);
		l.setPadding(new Insets(0, 0, 10, 0));
		l.setFont(new Font("Arial", 16));
		deseti.getChildren().add(l);
		
		//ocjenjivanje profesora
		ArrayList<Predmet> list = new ArrayList<Predmet>();   //lista svih predmeta datog ucenika iz kojih ima bar 1 ocjenu ili 1 izostanak
		for(Predmet p: u.getPredmetiByIzostanci())
			if(!list.contains(p))
				list.add(p);
		for(Predmet p: u.getPredmetiByOcjena())
			if(!list.contains(p))
				list.add(p);
		Label rezultat = new Label("");
		rezultat.setTextFill(Color.WHITE);
		for(Predmet p: list) {
			Button bt = new Button(p.getNaziv());
			bt.setPrefWidth(120);
			bt.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
			bt.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if(!p.postojiOcjenaPredmeta(u, u.getProfesor(p))){
						Label l = new Label("Please grade your professor of " + bt.getText() + " from 1-5, 5 being the best grade.");
						l.setPadding(new Insets(20, 0, 10, 0));
						l.setTextFill(Color.WHITE);
						jedanaesti.getChildren().clear();
						jedanaesti.getChildren().add(l);
						Label greska = new Label("");
						greska.setTextFill(Color.WHITE);
						/*for(Pitanje pi: Pitanje.getSvaPitanja()) {
							HBox red = new HBox(30);
							Label pitanje = new Label(pi.getPitanje());
							pitanje.setTextFill(Color.WHITE);
							ToggleGroup tg = new ToggleGroup();
							RadioButton rb1 = new RadioButton("1");
							rb1.setTextFill(Color.WHITE);
							rb1.setToggleGroup(tg);
							RadioButton rb2 = new RadioButton("2");
							rb2.setTextFill(Color.WHITE);
							rb2.setToggleGroup(tg);
							RadioButton rb3 = new RadioButton("3");
							rb3.setTextFill(Color.WHITE);
							rb3.setToggleGroup(tg);
							RadioButton rb4 = new RadioButton("4");
							rb4.setTextFill(Color.WHITE);
							rb4.setToggleGroup(tg);
							RadioButton rb5 = new RadioButton("5");
							rb5.setTextFill(Color.WHITE);
							rb5.setToggleGroup(tg);
							jedanaesti.getChildren().add(pitanje);
							red.getChildren().addAll(rb1, rb2, rb3, rb4, rb5);
							jedanaesti.getChildren().add(red);
						}*/
						
						//first question
						ArrayList<RadioButton> rbl1=new ArrayList<RadioButton>();
						Label pitanje1 = new Label(Pitanje.getPitanje(1).getPitanje());
						pitanje1.setTextFill(Color.WHITE);
						ToggleGroup tg = new ToggleGroup();
						RadioButton rb1 = new RadioButton("1");
						rb1.setTextFill(Color.WHITE);
						rb1.setToggleGroup(tg);
						rbl1.add(rb1);
						RadioButton rb2 = new RadioButton("2");
						rb2.setTextFill(Color.WHITE);
						rb2.setToggleGroup(tg);
						rbl1.add(rb2);
						RadioButton rb3 = new RadioButton("3");
						rb3.setTextFill(Color.WHITE);
						rb3.setToggleGroup(tg);
						rbl1.add(rb3);
						RadioButton rb4 = new RadioButton("4");
						rb4.setTextFill(Color.WHITE);
						rb4.setToggleGroup(tg);
						rbl1.add(rb4);
						RadioButton rb5 = new RadioButton("5");
						rb5.setTextFill(Color.WHITE);
						rb5.setToggleGroup(tg);
						rbl1.add(rb5);
						HBox red1 = new HBox(30);
						red1.getChildren().addAll(rb1, rb2, rb3, rb4, rb5);
						jedanaesti.getChildren().addAll(pitanje1, red1);         
						//second
						ArrayList<RadioButton> rbl2=new ArrayList<RadioButton>();
						Label pitanje2 = new Label(Pitanje.getPitanje(2).getPitanje());
						pitanje2.setTextFill(Color.WHITE);
						ToggleGroup tg2 = new ToggleGroup();
						RadioButton rb6 = new RadioButton("1");
						rb6.setTextFill(Color.WHITE);
						rb6.setToggleGroup(tg2);
						rbl2.add(rb6);
						RadioButton rb7 = new RadioButton("2");
						rb7.setTextFill(Color.WHITE);
						rb7.setToggleGroup(tg2);
						rbl2.add(rb7);
						RadioButton rb8 = new RadioButton("3");
						rb8.setTextFill(Color.WHITE);
						rb8.setToggleGroup(tg2);
						rbl2.add(rb8);
						RadioButton rb9 = new RadioButton("4");
						rb9.setTextFill(Color.WHITE);
						rb9.setToggleGroup(tg2);
						rbl2.add(rb9);
						RadioButton rb10 = new RadioButton("5");
						rb10.setTextFill(Color.WHITE);
						rb10.setToggleGroup(tg2);
						rbl2.add(rb10);
						HBox red2 = new HBox(30);
						red2.getChildren().addAll(rb6, rb7, rb8, rb9, rb10);
						jedanaesti.getChildren().addAll(pitanje2, red2);         
						//third
						ArrayList<RadioButton> rbl3=new ArrayList<RadioButton>();
						Label pitanje3 = new Label(Pitanje.getPitanje(3).getPitanje());
						pitanje3.setTextFill(Color.WHITE);
						ToggleGroup tg3 = new ToggleGroup();
						RadioButton rb11 = new RadioButton("1");
						rb11.setTextFill(Color.WHITE);
						rb11.setToggleGroup(tg3);
						rbl3.add(rb11);
						RadioButton rb12 = new RadioButton("2");
						rb12.setTextFill(Color.WHITE);
						rb12.setToggleGroup(tg3);
						rbl3.add(rb12);
						RadioButton rb13 = new RadioButton("3");
						rb13.setTextFill(Color.WHITE);
						rb13.setToggleGroup(tg3);
						rbl3.add(rb13);
						RadioButton rb14 = new RadioButton("4");
						rb14.setTextFill(Color.WHITE);
						rb14.setToggleGroup(tg3);
						rbl3.add(rb14);
						RadioButton rb15 = new RadioButton("5");
						rb15.setTextFill(Color.WHITE);
						rb15.setToggleGroup(tg3);
						rbl3.add(rb15);
						HBox red3 = new HBox(30);
						red3.getChildren().addAll(rb11, rb12, rb13, rb14, rb15);
						jedanaesti.getChildren().addAll(pitanje3, red3);         
						//fourth
						ArrayList<RadioButton> rbl4=new ArrayList<RadioButton>();
						Label pitanje4 = new Label(Pitanje.getPitanje(4).getPitanje());
						pitanje4.setTextFill(Color.WHITE);
						ToggleGroup tg4 = new ToggleGroup();
						RadioButton rb16 = new RadioButton("1");
						rb16.setTextFill(Color.WHITE);
						rb16.setToggleGroup(tg4);
						rbl4.add(rb16);
						RadioButton rb17 = new RadioButton("2");
						rb17.setTextFill(Color.WHITE);
						rb17.setToggleGroup(tg4);
						rbl4.add(rb17);
						RadioButton rb18 = new RadioButton("3");
						rb18.setTextFill(Color.WHITE);
						rb18.setToggleGroup(tg4);
						rbl4.add(rb18);
						RadioButton rb19 = new RadioButton("4");
						rb19.setTextFill(Color.WHITE);
						rb19.setToggleGroup(tg4);
						rbl4.add(rb19);
						RadioButton rb20 = new RadioButton("5");
						rb20.setTextFill(Color.WHITE);
						rb20.setToggleGroup(tg4);
						rbl4.add(rb20);
						HBox red4 = new HBox(30);
						red4.getChildren().addAll(rb16, rb17, rb18, rb19, rb20);
						jedanaesti.getChildren().addAll(pitanje4, red4);         
						
						HBox potvrdihb = new HBox(10);
						potvrdihb.setPadding(new Insets(10, 0, 8, 0));
						Button potvrdi = new Button("Confirm");
						potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
						potvrdihb.getChildren().add(potvrdi);
						potvrdihb.getChildren().add(greska);
						jedanaesti.getChildren().add(potvrdihb);
						potvrdi.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {  
								int br=0;
								ocjena=0;
								for(int i=0; i<5; i++) {
									if(!rbl1.get(i).isSelected())
										greska.setText("Please answer all questions.");
									else {
										br++;
										ocjene[0]=(i+1);
										ocjena+=(i+1);
									}
								}
								for(int i=0; i<5; i++) {
									if(!rbl2.get(i).isSelected())
										greska.setText("Please answer all questions.");
									else {
										br++;
										ocjene[1]=(i+1);
										ocjena+=(i+1);
									}
								}
								for(int i=0; i<5; i++) {
									if(!rbl3.get(i).isSelected())
										greska.setText("Please answer all questions.");
									else {
										br++;
										ocjene[2]=(i+1);
										ocjena+=(i+1);
									}
								}
								for(int i=0; i<5; i++) {
									if(!rbl4.get(i).isSelected())
										greska.setText("Please answer all questions.");
									else {
										br++;
										ocjene[3]=(i+1);
										ocjena+=(i+1);
									}
								}
								if(br>=4) {
									jedanaesti.getChildren().clear();
									rezultat.setText("You have successfully graded professor of this subject with "+(ocjena+0.0)/4);
									jedanaesti.getChildren().add(rezultat);
									konekcija.novaOcjenaPredmeta(u.getId(), p.getPredmetUSkoli(u.getSkola()).getId(), 1, ocjene[0]);
									konekcija.novaOcjenaPredmeta(u.getId(), p.getPredmetUSkoli(u.getSkola()).getId(), 2, ocjene[1]);
									konekcija.novaOcjenaPredmeta(u.getId(), p.getPredmetUSkoli(u.getSkola()).getId(), 3, ocjene[2]);
									konekcija.novaOcjenaPredmeta(u.getId(), p.getPredmetUSkoli(u.getSkola()).getId(), 4, ocjene[3]);
								}
							}   
						}); 
						} 
						else {
							jedanaesti.getChildren().clear();
							Predmet pr = Predmet.getPredmet(bt.getText(), u);  //vrati drugu matematiku npr koja nam ne treba ako nemam i ucenika kao param
							rezultat.setText("Professor of " + pr.getNaziv() + " is already graded with " + pr.getOcjenaPredmeta(u, u.getProfesor(pr)));
							jedanaesti.getChildren().add(rezultat);
						}
				} 
			});
			bt.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								bt.setEffect(new Glow());
							}
			});
			bt.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				          public void handle(MouseEvent e) {
				            bt.setEffect(null);
				          }
			});
			deseti.getChildren().add(bt);        
			
		}
		Button zatvori3 = new Button("Hide grading box");
		zatvori3.setPrefWidth(120);
		zatvori3.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		zatvori3.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							zatvori3.setEffect(new Glow());
						}
		});
		zatvori3.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            zatvori3.setEffect(null);
			          }
		});
		zatvori3.setOnAction(e -> jedanaesti.getChildren().clear());
		deseti.getChildren().add(zatvori3);
		//kraj ocjenjivanja profesora
		
		VBox dvanaesti = new VBox(10);
		VBox trinaesti = new VBox(10);
		trinaesti.setPadding(new Insets(10, 5, 0, 0));
		Separator separator5 = new Separator();
		separator5.setStyle("-fx-background-color: indigo;");
		separator5.setOpacity(0.5);
		separator5.setPadding(new Insets(0, 0, 10, 0));
		Label sifra = new Label("Change password");
		sifra.setTextFill(Color.WHITE);
		sifra.setMaxWidth(Double.MAX_VALUE);
		sifra.setAlignment(Pos.CENTER);
		sifra.setPadding(new Insets(0, 0, 10, 0));
		sifra.setFont(new Font("Arial", 16));
		Button promjenaSifre = new Button("Password box");
		promjenaSifre.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		promjenaSifre.setPrefWidth(120);
		promjenaSifre.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							promjenaSifre.setEffect(new Glow());
						}
		});
		promjenaSifre.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            promjenaSifre.setEffect(null);
			          }
		});
		promjenaSifre.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				CornerRadii corn = new CornerRadii(15);
				Background background = new Background(new BackgroundFill(Color.BLACK, corn, Insets.EMPTY));
				PasswordField pf = new PasswordField();
				pf.setPromptText("Insert current password");
				pf.setBackground(background);
				pf.setStyle("-fx-text-fill: white;");
				pf.setMaxHeight(40);
				pf.setMinHeight(40);
				pf.setPrefHeight(Region.USE_COMPUTED_SIZE);
				CheckBox checkBox = new CheckBox("Show password");
				checkBox.setStyle("-fx-text-fill: white;");
				checkBox.setPadding(new Insets(0, 0, 5, 5));
				TextField textField = new TextField();
				textField.setBackground(background);
				textField.setStyle("-fx-text-fill: white;");
				textField.setPromptText("Insert current password");
				textField.setMaxHeight(40);
				textField.setMinHeight(40);
				textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
				textField.setManaged(false);
				textField.setVisible(false);
				textField.managedProperty().bind(checkBox.selectedProperty());
				textField.visibleProperty().bind(checkBox.selectedProperty());
				pf.managedProperty().bind(checkBox.selectedProperty().not());
				pf.visibleProperty().bind(checkBox.selectedProperty().not());
				textField.textProperty().bindBidirectional(pf.textProperty());
				PasswordField pf2 = new PasswordField();
				pf2.setPromptText("Insert new password");
				pf2.setBackground(background);
				pf2.setStyle("-fx-text-fill: white;");
				pf2.setMaxHeight(40);
				pf2.setMinHeight(40);
				pf2.setPrefHeight(Region.USE_COMPUTED_SIZE);
				CheckBox checkBox2 = new CheckBox("Show password");
				checkBox2.setStyle("-fx-text-fill: white;");
				checkBox2.setPadding(new Insets(0, 0, 5, 5));
				TextField textField2 = new TextField();
				textField2.setBackground(background);
				textField2.setStyle("-fx-text-fill: white;");
				textField2.setPromptText("Insert new password");
				textField2.setMaxHeight(40);
				textField2.setMinHeight(40);
				textField2.setPrefHeight(Region.USE_COMPUTED_SIZE);
				textField2.setManaged(false);
				textField2.setVisible(false);
				textField2.managedProperty().bind(checkBox2.selectedProperty());
				textField2.visibleProperty().bind(checkBox2.selectedProperty());
				pf2.managedProperty().bind(checkBox2.selectedProperty().not());
				pf2.visibleProperty().bind(checkBox2.selectedProperty().not());
				textField2.textProperty().bindBidirectional(pf2.textProperty());
				PasswordField pf3 = new PasswordField();
				pf3.setPromptText("Repeat new password");
				pf3.setBackground(background);
				pf3.setStyle("-fx-text-fill: white;");
				pf3.setMaxHeight(40);
				pf3.setMinHeight(40);
				pf3.setPrefHeight(Region.USE_COMPUTED_SIZE);
				CheckBox checkBox3 = new CheckBox("Show password");
				checkBox3.setStyle("-fx-text-fill: white;");
				checkBox3.setPadding(new Insets(0, 0, 5, 5));
				TextField textField3 = new TextField();
				textField3.setBackground(background);
				textField3.setStyle("-fx-text-fill: white;");
				textField3.setPromptText("Repeat new password");
				textField3.setMaxHeight(40);
				textField3.setMinHeight(40);
				textField3.setPrefHeight(Region.USE_COMPUTED_SIZE);
				textField3.setManaged(false);
				textField3.setVisible(false);
				textField3.managedProperty().bind(checkBox3.selectedProperty());
				textField3.visibleProperty().bind(checkBox3.selectedProperty());
				pf3.managedProperty().bind(checkBox3.selectedProperty().not());
				pf3.visibleProperty().bind(checkBox3.selectedProperty().not());
				textField3.textProperty().bindBidirectional(pf3.textProperty());
				TextField mejl = new TextField("");
				mejl.setBackground(background);
				mejl.setStyle("-fx-text-fill: white;");
				mejl.setMaxHeight(40);
				mejl.setMinHeight(40);
				mejl.setPrefHeight(Region.USE_COMPUTED_SIZE);
				mejl.setPromptText("Insert your e-mail");
				Label rez = new Label("");
				rez.setTextFill(Color.WHITE);
				Label gr = new Label("");
				gr.setTextFill(Color.WHITE);
				HBox hbpotvrdi = new HBox(5);
				hbpotvrdi.setPadding(new Insets(10, 0, 10, 0));
				Button potvrdi = new Button("Confirm");
				potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				hbpotvrdi.getChildren().addAll(potvrdi, gr);
				potvrdi.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(pf.getText().trim().equals("") || pf2.getText().equals("") || pf3.getText().equals("") || mejl.getText().trim().equals("")) {
							gr.setText("Please fill all fields.");
						} else
							try {
								if(!LoginKontroler.Login(u.getPristupniPodaci().getKorisnickoIme(), pf.getText().trim(), konekcija))
									gr.setText("Current password is wrong. Please try again.");
								else if(!pf2.getText().trim().equals(pf3.getText().trim()))
									gr.setText("New passwords don't match. Please try again.");
								else if(!(mejl.getText().contains("@gmail.com") || mejl.getText().contains("@yahoo.com") || mejl.getText().contains("@student.pmf.unibl.org") || mejl.getText().contains("@pmf.unibl.org") || mejl.getText().contains("@hotmail.com")))
									gr.setText("E-mail invalid. Please try again.");
								else {
									gr.setText("");
									trinaesti.getChildren().clear();
									//JavaMail.sendMail(mejl.getText(), "Hey " + u.getIme() + " " + u.getPrezime() + ", \n you have successfully changed your password to " + pf2.getText());
									JavaMail.sendMail("sara.majkic1998@gmail.com", "Hey " + u.getIme() + " " + u.getPrezime() + ", \n you have successfully changed your password to " + pf2.getText());
									//u.getPristupniPodaci().setSifra();
									konekcija.setSifra(u.getPristupniPodaci().getKorisnickoIme(), pf2.getText());
									rez.setText("You have successfully changed your password. " +"\nConfirmation e-mail has been sent to " + mejl.getText() + ".");
									trinaesti.getChildren().add(rez);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
					}
				});
				trinaesti.getChildren().addAll(pf, textField, checkBox, pf2, textField2, checkBox2, pf3, textField3, checkBox3, mejl, hbpotvrdi);
			}
		});
		dvanaesti.getChildren().addAll(separator5, sifra, promjenaSifre);
		Button sakrij = new Button("Hide password box");
		sakrij.setOnAction(e -> trinaesti.getChildren().clear());
		sakrij.setPrefWidth(120);
		sakrij.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		sakrij.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				trinaesti.getChildren().clear();
			}
		});
		sakrij.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							sakrij.setEffect(new Glow());
						}
		});
		sakrij.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            sakrij.setEffect(null);
			          }
		});
		dvanaesti.getChildren().add(sakrij);
		
		VBox last = new VBox(35);
		Separator separator6 = new Separator();
		separator6.setStyle("-fx-background-color: indigo;");
		separator6.setOpacity(0.5);
		separator6.setPadding(new Insets(0, 0, 10, 0));
		HBox hb = new HBox(80);
		Label inf = new Label("For any additional questions, please contact class teacher.");
		inf.setPadding(new Insets(10, 0, 0, 0));
		inf.setStyle("-fx-text-fill: blueviolet;");
		Button odjava = new Button("Log out");
		odjava.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		odjava.setEffect(new Reflection());
		odjava.setMaxHeight(30);
		odjava.setMinHeight(30);
		odjava.setPrefHeight(Region.USE_COMPUTED_SIZE);
		odjava.setMaxWidth(Double.MAX_VALUE);
		odjava.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							odjava.setEffect(new Glow());
						}
		});
		odjava.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            odjava.setEffect(new Reflection());
			          }
		});
		odjava.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
				alert.setHeaderText("Are you sure you want to log out");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
				    stage2.close();
				    Stage primaryStage = new Stage();
				    try {
						start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		ImageView imageView2 = new ImageView();
		Image image2 = new Image(new FileInputStream("D:\\ucenik.png"));
		imageView2.setImage(image2);
		imageView2.setFitHeight(190); 
	    imageView2.setFitWidth(310); 
	    //imageView.setPreserveRatio(true);  

		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(inf, odjava);
		last.getChildren().addAll(separator6, hb, imageView2);
		last.setAlignment(Pos.CENTER);
		
		down.getChildren().addAll(ispis, sesti, sedmi, osmi, deveti, deseti, jedanaesti, dvanaesti, trinaesti, last);
		root.getChildren().addAll(top, down);
		root.setStyle("-fx-background-color: rgb(45,33,70);");
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				stage2.setX(event.getScreenX() - xOffset);
				stage2.setY(event.getScreenY() - yOffset);
			}
			
		});
		
		stage2.initStyle(StageStyle.TRANSPARENT);
		//stage2.setTitle("Dnevnik ucenika");
		stage2.setScene(ucenik);
		stage2.show();
	}

	//------------------------------------------------------------------profesor scena-------------------------------------------------------------------
	private void profesorScena(String korisnickoIme) throws FileNotFoundException {
		
		Profesor p = Profesor.getProfesor(korisnickoIme);
		Stage stage3 = new Stage();
		VBox root = new VBox(35);
		root.setPadding(new Insets(25, 30, 100, 30));
		ScrollPane scrollPane = new ScrollPane();

		HBox top = new HBox(160);
		Label naslov = new Label("Welcome to professor diary");
		naslov.setTextFill(Color.WHITE);
		naslov.setFont(new Font("Arial", 20));
		naslov.setPadding(new Insets(5, 0, 0, 0));
		Button close = new Button("Exit");
		close.setEffect(new Reflection());
		close.setOnAction( e -> stage3.close() );
		close.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		close.setMaxHeight(30);
		close.setMinHeight(30);
		close.setPrefHeight(Region.USE_COMPUTED_SIZE);
		close.setMaxWidth(Double.MAX_VALUE);
		close.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							close.setEffect(new Glow());
						}
		});
		close.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            close.setEffect(new Reflection());
			          }
		});
		top.getChildren().addAll(naslov, close);
		
		Separator separator = new Separator();
		separator.setStyle("-fx-background-color: indigo;");
		separator.setOpacity(0.5);
		separator.setPadding(new Insets(0, 0, 10, 0));
		
		//ispis osnovnih podataka profesora
		VBox down = new VBox(9);
		Label op = new Label("Basic information");
		op.setTextFill(Color.WHITE);
		op.setMaxWidth(Double.MAX_VALUE);
		op.setAlignment(Pos.CENTER);
		op.setPadding(new Insets(0, 0, 10, 0));
		op.setFont(new Font("Arial", 16));
		HBox prvi = new HBox(5);
		Label ime = new Label("Name: ");
		ime.setTextFill(Color.WHITE);
		Label ispisImena = new Label(p.getIme());
		ispisImena.setTextFill(Color.WHITE);
		prvi.getChildren().addAll(ime, ispisImena);
		HBox drugi = new HBox(5);
		Label prezime = new Label("Surname: ");
		prezime.setTextFill(Color.WHITE);
		Label ispisPrezimena = new Label(p.getPrezime());
		ispisPrezimena.setTextFill(Color.WHITE);
		drugi.getChildren().addAll(prezime, ispisPrezimena);
		VBox treci = new VBox(5);
		VBox cetvrti = new VBox(5);  //cetvrti je "prozor" za pojedinacne predmete, prikaz svih ucenika koji imaju ocjenu ili izostanak iz tog predmeta i prikaz ocjena tog predmeta
		Separator separator2 = new Separator();
		separator2.setStyle("-fx-background-color: blueviolet;");
		Separator separator3 = new Separator();
		separator3.setStyle("-fx-background-color: blueviolet;");
		VBox peti = new VBox(5);   //peti je "prozor" za pojedinacne ucenike, button za prikaz svih ocjena ucenika iz tog predmeta, bt za dodavanje ocjene ili izostanka
		VBox sesti = new VBox(0);
		for(Skola s: p.getSkole()) {
			Label l = new Label(s.getNaziv() + ", " + s.getMjesto() + ", " + s.getGrad() + ": ");
			l.setTextFill(Color.WHITE);
			VBox vb = new VBox(5);
			vb.getChildren().add(l);
			for(Predmet pr: p.getPredmeti(s)) {
				Button bt = new Button(pr.getNaziv() + " " + pr.getRazred());
				bt.setPrefWidth(120);
				bt.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				bt.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						//cetvrti.getChildren().clear();
						//cetvrti.setStyle("-fx-border-width: 1; -fx-border-color: blueviolet");
						//cetvrti.setPadding(new Insets(7, 7, 7, 7));
						cetvrti.getChildren().clear();
						peti.getChildren().clear();
						sesti.getChildren().clear();
						Label naslov = new Label("Box for subject " + pr.getNaziv() + " " +pr.getRazred() + ", " + s.getNaziv() + ", " + s.getMjesto() + ", " + s.getGrad() + ": ");
						naslov.setTextFill(Color.WHITE);
						Button ocjene = new Button("Subject grades");
						ocjene.setPrefWidth(120);
						ocjene.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
						cetvrti.getChildren().addAll(separator2, naslov);
						if(!pr.getUcenici(p, s).isEmpty())
							cetvrti.getChildren().add(ocjene);
						ocjene.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
								sesti.getChildren().clear();
								peti.getChildren().add(separator3);
								Label l = new Label("Grades for your class " + pr.getNaziv() + " " + pr.getRazred() + " assigned to you by students:");
								l.setTextFill(Color.WHITE);
								peti.getChildren().add(l);
								Label ocj = new Label("");
								String str = "";
								double o = 0;
								ocj.setTextFill(Color.WHITE);
								/*for(OcjenaPredmeta ocjena: OcjenaPredmeta.getSveOcjenePredmeta()) {
									for(Ucenik u: pr.getUcenici(p, s))
										if(ocjena.getPredmetUSkoli().getPredmet().equals(pr) && ocjena.getPredmetUSkoli().getProfesor().equals(p) && ocjena.getPredmetUSkoli().getSkola().equals(s) && ocjena.getUcenik().equals(u)) {
											str += "" + ocjena.getOcjena() + "\n";
									}   
								}*/
								for(Ucenik u: pr.getUcenici(p, s)) {
									if(pr.getOcjenaPredmeta(u, p)!=0) {
										o = pr.getOcjenaPredmeta(u, p);
										str += o + "\n";
									}
								}
								if(str.equals(""))
									ocj.setText("There are no grades in database for this class.");
								else
									ocj.setText(str);
								peti.getChildren().add(ocj);
							}
						});
						ocjene.addEventHandler(MouseEvent.MOUSE_ENTERED,
								new EventHandler<MouseEvent>() {
										public void handle(MouseEvent e) {
											ocjene.setEffect(new Glow());
										}
						});
						ocjene.addEventHandler(MouseEvent.MOUSE_EXITED,
								new EventHandler<MouseEvent>() {
							          public void handle(MouseEvent e) {
							            ocjene.setEffect(null);
							          }
						});
						//cetvrti.getChildren().add(separator2);
						if(pr.getUcenici(p, s).isEmpty()) {
							Label l = new Label("There are no students in database for this subject.");
							l.setTextFill(Color.WHITE);
							cetvrti.getChildren().add(l);
						}
						else {
							Label lu = new Label("Students with at least one grade and/or absence: ");
							lu.setTextFill(Color.WHITE);
							cetvrti.getChildren().add(lu);
						}
						for(Ucenik u: pr.getUcenici(p, s)) {
							Button bt2 = new Button(u.getIme() + " " + u.getPrezime());
							bt2.setPrefWidth(120);
							bt2.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
							bt2.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									peti.getChildren().clear();
									sesti.getChildren().clear();
									Label naslov2 = new Label("Box for student " + u.getIme() + " " + u.getPrezime() + ": ");
									naslov2.setTextFill(Color.WHITE);
									peti.getChildren().addAll(separator3, naslov2);
									Button ocjene = new Button("All grades");
									ocjene.setPrefWidth(120);
									ocjene.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
									ocjene.setOnAction(new EventHandler<ActionEvent>() {
										public void handle(ActionEvent event) {
											sesti.getChildren().clear();
											if(u.getSveOcjene().isEmpty()) {
												Label gr = new Label("This student does not have any grades yet.");
												gr.setTextFill(Color.WHITE);
												sesti.getChildren().add(gr);
											}
											else {
												//Label l = new Label("Showing all grades for student " + u.getIme() + " " + u.getPrezime() + " for subject " + pr.getNaziv() + " " + pr.getRazred() + " :");
												//l.setTextFill(Color.WHITE);
												//sesti.getChildren().add(l);
												BorderPane bPane = new BorderPane();
												Label grade = new Label("Grade");
												grade.setPadding(new Insets(0, 90, 0, 90));
												grade.setTextFill(Color.WHITE);
												Label date = new Label("Date");
												date.setTextFill(Color.WHITE);
												date.setPadding(new Insets(0, 100, 0, 80));
												bPane.setLeft(grade);
												bPane.setRight(date);
												sesti.getChildren().add(bPane);
												for(Ocjena o: u.getOcjeneIzPredmeta(pr.getNaziv())) {
													if(u.getOcjeneIzPredmeta(pr.getNaziv()).isEmpty()) {
														Label ll = new Label("This student does not have any grades.");
														sesti.getChildren().add(ll);
													}	
													BorderPane bp = new BorderPane();
													Label ocj = new Label(""+o.getOcjena());
													ocj.setTextFill(Color.WHITE);
													ocj.setPadding(new Insets(0, 105, 0, 105));
													ocj.setStyle("-fx-border-width: 1; -fx-border-color: white");
													Label dat = new Label(""+o.getDatum());
													dat.setTextFill(Color.WHITE);
													dat.setPadding(new Insets(0, 80, 0, 80));
													dat.setStyle("-fx-border-width: 1; -fx-border-color: white");
													bp.setLeft(ocj);
													bp.setCenter(dat);
													sesti.getChildren().add(bp);
												}
											}
										}
									});
									ocjene.addEventHandler(MouseEvent.MOUSE_ENTERED,
											new EventHandler<MouseEvent>() {
													public void handle(MouseEvent e) {
														ocjene.setEffect(new Glow());
													}
									});
									ocjene.addEventHandler(MouseEvent.MOUSE_EXITED,
											new EventHandler<MouseEvent>() {
										          public void handle(MouseEvent e) {
										            ocjene.setEffect(null);
										          }
									});
									peti.getChildren().add(ocjene);
									Button ocijeni = new Button("Grade student");
									ocijeni.setPrefWidth(120);
									ocijeni.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
									Button izostanak = new Button("Add absence");
									izostanak.setPrefWidth(120);
									izostanak.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
									ocijeni.setOnAction(new EventHandler<ActionEvent>() {
										public void handle(ActionEvent event) {
											sesti.getChildren().clear();
											VBox box = new VBox(8);  //dodajem u sesti zbog pedinga koji mi je potreban kod dodavanja ocjene i izostanka
											Label l = new Label("Choose grade and date");
											l.setTextFill(Color.WHITE);
											sesti.getChildren().add(l);
											HBox red = new HBox(7);
											ToggleGroup tg = new ToggleGroup();
											RadioButton rb1 = new RadioButton("1");
											rb1.setTextFill(Color.WHITE);
											rb1.setToggleGroup(tg);
											RadioButton rb2 = new RadioButton("2");
											rb2.setTextFill(Color.WHITE);
											rb2.setToggleGroup(tg);
											RadioButton rb3 = new RadioButton("3");
											rb3.setTextFill(Color.WHITE);
											rb3.setToggleGroup(tg);
											RadioButton rb4 = new RadioButton("4");
											rb4.setTextFill(Color.WHITE);
											rb4.setToggleGroup(tg);
											RadioButton rb5 = new RadioButton("5");
											rb5.setTextFill(Color.WHITE);
											rb5.setToggleGroup(tg);
											red.getChildren().addAll(rb1, rb2, rb3, rb4, rb5);
											ArrayList<RadioButton> alb = new ArrayList<RadioButton>();
											alb.add(rb1);
											alb.add(rb2);
											alb.add(rb3);
											alb.add(rb4);
											alb.add(rb5); 
											red.setPadding(new Insets(5, 0, 0, 0));
											DatePicker dp = new DatePicker(LocalDate.now());
											dp.setPadding(new Insets(5, 0, 0, 5));
											Button potvrdi = new Button("Confirm");
											potvrdi.setPrefWidth(120);
											potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
											Label greska = new Label("");
											greska.setTextFill(Color.WHITE);
											box.getChildren().addAll(red, dp, potvrdi, greska);
											potvrdi.setOnAction(new EventHandler<ActionEvent>() {
												public void handle(ActionEvent event) {
													for(int i=0; i<5; i++) {
														if(!alb.get(i).isSelected())
															greska.setText("Please insert grade.");
														else if(Izostanci.postojiIz(dp.getValue(), pr.getPredmetUSkoli(s), u)) {
															greska.setText("Student was absent on this date.");
														}
														else if(u.brojDnevnihOcjena(dp.getValue())>=2) {
															greska.setText("Student already got two grades on this date.");
														}
														else if(u.razlikaUDanima(dp.getValue(), u.datumPosljednjeOcjene(pr))<0) {
															greska.setText("Inserted date is not newer than the oldest date for this subject. \nIt has to be at least 7 days newer.");
														}
														else if(dp.getValue().compareTo(LocalDate.now())>=1) {
															greska.setText("Inserted date can not be newer than today.");
														}
														else if(u.razlikaUDanima(dp.getValue(), u.datumPosljednjeOcjene(pr))>=0 && u.razlikaUDanima(dp.getValue(), u.datumPosljednjeOcjene(pr))<7) {
															greska.setText("This student got a grade for this subject less than 7 days ago.");
														}
														else {
															oc = i+1;
															sesti.getChildren().clear();
															Label lab = new Label("You have successfully graded student " + u.getIme() + " " + u.getPrezime() + " for subject " + pr.getNaziv() + " with " + oc + " .");
															lab.setTextFill(Color.WHITE);
															sesti.getChildren().add(lab);
															konekcija.novaOcjenaUcenika(u.getId(), pr.getPredmetUSkoli(s).getId(), oc, dp.getValue().toString());
														}
													}
												}
											});
											potvrdi.addEventHandler(MouseEvent.MOUSE_ENTERED,
													new EventHandler<MouseEvent>() {
															public void handle(MouseEvent e) {
																potvrdi.setEffect(new Glow());
															}
											});
											potvrdi.addEventHandler(MouseEvent.MOUSE_EXITED,
													new EventHandler<MouseEvent>() {
												          public void handle(MouseEvent e) {
												            potvrdi.setEffect(null);
												          }
											});
											sesti.getChildren().add(box);
										}
									});
									ocijeni.addEventHandler(MouseEvent.MOUSE_ENTERED,
											new EventHandler<MouseEvent>() {
													public void handle(MouseEvent e) {
														ocijeni.setEffect(new Glow());
													}
									});
									ocijeni.addEventHandler(MouseEvent.MOUSE_EXITED,
											new EventHandler<MouseEvent>() {
										          public void handle(MouseEvent e) {
										            ocijeni.setEffect(null);
										          }
									});
									izostanak.setOnAction(new EventHandler<ActionEvent>() {
										public void handle(ActionEvent event) {
											sesti.getChildren().clear();
											VBox box = new VBox(8);
											Label l2 = new Label("Choose date");
											l2.setTextFill(Color.WHITE);
											DatePicker dp2 = new DatePicker(LocalDate.now());
											dp2.setPadding(new Insets(5, 0, 0, 5));
											Button potvrdi2 = new Button("Confirm");
											potvrdi2.setPrefWidth(120);
											potvrdi2.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
											Label gr2 = new Label("");
											gr2.setTextFill(Color.WHITE);
											box.getChildren().addAll(l2, dp2, potvrdi2, gr2);
											sesti.getChildren().add(box);
											potvrdi2.setOnAction(new EventHandler<ActionEvent>() {
												public void handle(ActionEvent event) {
													//sesti.getChildren().clear();
													if(dp2.getValue().compareTo(LocalDate.now())>=1) {
														gr2.setText("Inserted date can not be newer than today.");
													}
													else if(Izostanci.postojiIz(dp2.getValue(), pr.getPredmetUSkoli(s), u)) {
														gr2.setText("This student already has absence on this date for this class.");
													}
													else {
														sesti.getChildren().clear();
														Label lab = new Label("You have successfully added absence for student " + u.getIme() + " " + u.getPrezime() + " on " + dp2.getValue());
														lab.setTextFill(Color.WHITE);
														sesti.getChildren().add(lab);
														konekcija.noviIzostanakUcenika(u.getId(), pr.getPredmetUSkoli(s).getId(), dp2.getValue().toString());
													}
												}
											});
											potvrdi2.addEventHandler(MouseEvent.MOUSE_ENTERED,
													new EventHandler<MouseEvent>() {
															public void handle(MouseEvent e) {
																potvrdi2.setEffect(new Glow());
															}
											});
											potvrdi2.addEventHandler(MouseEvent.MOUSE_EXITED,
													new EventHandler<MouseEvent>() {
												          public void handle(MouseEvent e) {
												            potvrdi2.setEffect(null);
												          }
											});
										}
									});
									izostanak.addEventHandler(MouseEvent.MOUSE_ENTERED,
											new EventHandler<MouseEvent>() {
													public void handle(MouseEvent e) {
														izostanak.setEffect(new Glow());
													}
									});
									izostanak.addEventHandler(MouseEvent.MOUSE_EXITED,
											new EventHandler<MouseEvent>() {
										          public void handle(MouseEvent e) {
										            izostanak.setEffect(null);
										          }
									});
									peti.getChildren().addAll(ocijeni, izostanak);
								}
							});
							bt2.addEventHandler(MouseEvent.MOUSE_ENTERED,
									new EventHandler<MouseEvent>() {
											public void handle(MouseEvent e) {
												bt2.setEffect(new Glow());
											}
							});
							bt2.addEventHandler(MouseEvent.MOUSE_EXITED,
									new EventHandler<MouseEvent>() {
								          public void handle(MouseEvent e) {
								            bt2.setEffect(null);
								          }
							});
							/*Label lu = new Label("Students with at least one grade and/or absence: ");
							lu.setTextFill(Color.WHITE);
							cetvrti.getChildren().add(lu);*/
							cetvrti.getChildren().add(bt2);
							
							
						}
						Label ucenici = new Label("Students without any grades and absences: ");
						ucenici.setTextFill(Color.WHITE);
						cetvrti.getChildren().add(ucenici);
						for(Ucenik uc : Ucenik.getUceniciBezOcjeneBezIzostanka()) {
							Button bt3 = new Button(uc.getIme() + " " + uc.getPrezime());
							bt3.setPrefWidth(120);
							bt3.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
							bt3.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									peti.getChildren().clear();
									sesti.getChildren().clear();
									Label naslov2 = new Label("Box for student " + uc.getIme() + " " + uc.getPrezime() + ": ");
									naslov2.setTextFill(Color.WHITE);
									peti.getChildren().addAll(separator3, naslov2);
							Button ocijeni = new Button("Grade student");
							ocijeni.setPrefWidth(120);
							ocijeni.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
							Button izostanak = new Button("Add absence");
							izostanak.setPrefWidth(120);
							izostanak.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
							ocijeni.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									sesti.getChildren().clear();
									VBox box = new VBox(8);  //dodajem u sesti zbog pedinga koji mi je potreban kod dodavanja ocjene i izostanka
									Label l = new Label("Choose grade and date");
									l.setTextFill(Color.WHITE);
									sesti.getChildren().add(l);
									HBox red = new HBox(7);
									ToggleGroup tg = new ToggleGroup();
									RadioButton rb1 = new RadioButton("1");
									rb1.setTextFill(Color.WHITE);
									rb1.setToggleGroup(tg);
									RadioButton rb2 = new RadioButton("2");
									rb2.setTextFill(Color.WHITE);
									rb2.setToggleGroup(tg);
									RadioButton rb3 = new RadioButton("3");
									rb3.setTextFill(Color.WHITE);
									rb3.setToggleGroup(tg);
									RadioButton rb4 = new RadioButton("4");
									rb4.setTextFill(Color.WHITE);
									rb4.setToggleGroup(tg);
									RadioButton rb5 = new RadioButton("5");
									rb5.setTextFill(Color.WHITE);
									rb5.setToggleGroup(tg);
									red.getChildren().addAll(rb1, rb2, rb3, rb4, rb5);
									ArrayList<RadioButton> alb = new ArrayList<RadioButton>();
									alb.add(rb1);
									alb.add(rb2);
									alb.add(rb3);
									alb.add(rb4);
									alb.add(rb5); 
									red.setPadding(new Insets(5, 0, 0, 0));
									DatePicker dp = new DatePicker(LocalDate.now());
									dp.setPadding(new Insets(5, 0, 0, 5));
									Button potvrdi = new Button("Confirm");
									potvrdi.setPrefWidth(120);
									potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
									Label greska = new Label("");
									greska.setTextFill(Color.WHITE);
									box.getChildren().addAll(red, dp, potvrdi, greska);
									potvrdi.setOnAction(new EventHandler<ActionEvent>() {
										public void handle(ActionEvent event) {
											for(int i=0; i<5; i++) {
												if(!alb.get(i).isSelected())
													greska.setText("Please insert grade.");
												else if(Izostanci.postojiIz(dp.getValue(), pr.getPredmetUSkoli(s), uc)) {
													greska.setText("Student was absent on this date.");
												}
												else if(uc.brojDnevnihOcjena(dp.getValue())>=2) {
													greska.setText("Student already got two grades on this date.");
												}
												else if(uc.razlikaUDanima(dp.getValue(), uc.datumPosljednjeOcjene(pr))<0) {
													greska.setText("Inserted date is not newer than the oldest date for this subject. \nIt has to be at least 7 days newer.");
												}
												else if(dp.getValue().compareTo(LocalDate.now())>=1) {
													greska.setText("Inserted date can not be newer than today.");
												}
												else if(uc.razlikaUDanima(dp.getValue(), uc.datumPosljednjeOcjene(pr))>=0 && uc.razlikaUDanima(dp.getValue(), uc.datumPosljednjeOcjene(pr))<7) {
													greska.setText("This student got a grade for this subject less than 7 days ago.");
												}
												else {
													oc = i+1;
													sesti.getChildren().clear();
													Label lab = new Label("You have successfully graded student " + uc.getIme() + " " + uc.getPrezime() + " for subject " + pr.getNaziv() + " with " + oc + " .");
													lab.setTextFill(Color.WHITE);
													sesti.getChildren().add(lab);
													konekcija.novaOcjenaUcenika(uc.getId(), pr.getPredmetUSkoli(s).getId(), oc, dp.getValue().toString());
												}
											}
										}
									});
									potvrdi.addEventHandler(MouseEvent.MOUSE_ENTERED,
											new EventHandler<MouseEvent>() {
													public void handle(MouseEvent e) {
														potvrdi.setEffect(new Glow());
													}
									});
									potvrdi.addEventHandler(MouseEvent.MOUSE_EXITED,
											new EventHandler<MouseEvent>() {
										          public void handle(MouseEvent e) {
										            potvrdi.setEffect(null);
										          }
									});
									sesti.getChildren().add(box);
								}
							});
							ocijeni.addEventHandler(MouseEvent.MOUSE_ENTERED,
									new EventHandler<MouseEvent>() {
											public void handle(MouseEvent e) {
												ocijeni.setEffect(new Glow());
											}
							});
							ocijeni.addEventHandler(MouseEvent.MOUSE_EXITED,
									new EventHandler<MouseEvent>() {
								          public void handle(MouseEvent e) {
								            ocijeni.setEffect(null);
								          }
							});
							izostanak.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									sesti.getChildren().clear();
									VBox box = new VBox(8);
									Label l2 = new Label("Choose date");
									l2.setTextFill(Color.WHITE);
									DatePicker dp2 = new DatePicker(LocalDate.now());
									dp2.setPadding(new Insets(5, 0, 0, 5));
									Button potvrdi2 = new Button("Confirm");
									potvrdi2.setPrefWidth(120);
									potvrdi2.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
									Label gr2 = new Label("");
									gr2.setTextFill(Color.WHITE);
									box.getChildren().addAll(l2, dp2, potvrdi2, gr2);
									sesti.getChildren().add(box);
									potvrdi2.setOnAction(new EventHandler<ActionEvent>() {
										public void handle(ActionEvent event) {
											//sesti.getChildren().clear();
											if(dp2.getValue().compareTo(LocalDate.now())>=1) {
												gr2.setText("Inserted date can not be newer than today.");
											}
											else if(Izostanci.postojiIz(dp2.getValue(), pr.getPredmetUSkoli(s), uc)) {
												gr2.setText("This student already has absence on this date for this class.");
											}
											else {
												sesti.getChildren().clear();
												Label lab = new Label("You have successfully added absence for student " + uc.getIme() + " " + uc.getPrezime() + " on " + dp2.getValue());
												lab.setTextFill(Color.WHITE);
												sesti.getChildren().add(lab);
												konekcija.noviIzostanakUcenika(uc.getId(), pr.getPredmetUSkoli(s).getId(), dp2.getValue().toString());
											}
										}
									});
									potvrdi2.addEventHandler(MouseEvent.MOUSE_ENTERED,
											new EventHandler<MouseEvent>() {
													public void handle(MouseEvent e) {
														potvrdi2.setEffect(new Glow());
													}
									});
									potvrdi2.addEventHandler(MouseEvent.MOUSE_EXITED,
											new EventHandler<MouseEvent>() {
										          public void handle(MouseEvent e) {
										            potvrdi2.setEffect(null);
										          }
									});
								}
							});
							izostanak.addEventHandler(MouseEvent.MOUSE_ENTERED,
									new EventHandler<MouseEvent>() {
											public void handle(MouseEvent e) {
												izostanak.setEffect(new Glow());
											}
							});
							izostanak.addEventHandler(MouseEvent.MOUSE_EXITED,
									new EventHandler<MouseEvent>() {
								          public void handle(MouseEvent e) {
								            izostanak.setEffect(null);
								          }
							});
							peti.getChildren().addAll(ocijeni, izostanak);
						}
					});
							bt3.addEventHandler(MouseEvent.MOUSE_ENTERED,
									new EventHandler<MouseEvent>() {
											public void handle(MouseEvent e) {
												bt3.setEffect(new Glow());
											}
							});
							bt3.addEventHandler(MouseEvent.MOUSE_EXITED,
									new EventHandler<MouseEvent>() {
								          public void handle(MouseEvent e) {
								        	  bt3.setEffect(null);
								          }
							});
							cetvrti.getChildren().add(bt3);
						}
						Button sakrij2 = new Button("Hide student box");
						sakrij2.setPrefWidth(120);
						sakrij2.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
						sakrij2.setOnAction(e -> { 
							peti.getChildren().clear();
							sesti.getChildren().clear();
						}) ;
						sakrij2.addEventHandler(MouseEvent.MOUSE_ENTERED,
								new EventHandler<MouseEvent>() {
										public void handle(MouseEvent e) {
											sakrij2.setEffect(new Glow());
										}
						});
						sakrij2.addEventHandler(MouseEvent.MOUSE_EXITED,
								new EventHandler<MouseEvent>() {
							          public void handle(MouseEvent e) {
							            sakrij2.setEffect(null);
							          }
						});
						if(!pr.getUcenici(p, s).isEmpty())
							cetvrti.getChildren().add(sakrij2);
					}
				});
				bt.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									bt.setEffect(new Glow());
								}
				});
				bt.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					          public void handle(MouseEvent e) {
					            bt.setEffect(null);
					          }
				});
				vb.getChildren().add(bt);
			}
			treci.getChildren().add(vb);
		}
		Button sakrij = new Button("Hide subject box");
		sakrij.setPrefWidth(120);
		sakrij.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		sakrij.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				cetvrti.getChildren().clear();
				peti.getChildren().clear();
				sesti.getChildren().clear();
				//cetvrti.setBorder(null);
				//cetvrti.setPadding(new Insets(0));
				//cetvrti.setBorder(null);
				//cetvrti.getChildren().clear();
			}
		});
		sakrij.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							sakrij.setEffect(new Glow());
						}
		});
		sakrij.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            sakrij.setEffect(null);
			          }
		});
		treci.getChildren().add(sakrij);
		VBox sedmi = new VBox(5);
		VBox osmi = new VBox(5);
		Separator separator4 = new Separator();
		separator4.setStyle("-fx-background-color: indigo;");
		separator4.setOpacity(0.5);
		separator4.setPadding(new Insets(0, 0, 10, 0));
		
		//dodavanje novog ucenika
		Button ucenik = new Button("Add new student");
		ucenik.setPrefWidth(120);
		ucenik.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		ucenik.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				osmi.getChildren().clear();
				CornerRadii corn = new CornerRadii(15);
				Background background = new Background(new BackgroundFill(Color.BLACK, corn, Insets.EMPTY));
				Label l = new Label("Please fill information for a new student: ");
				l.setTextFill(Color.WHITE);
				TextField ime = new TextField("");
				ime.setBackground(background);
				ime.setStyle("-fx-text-fill: white;");
				ime.setMaxHeight(40);
				ime.setMinHeight(40);
				ime.setPrefHeight(Region.USE_COMPUTED_SIZE);
				ime.setPromptText("Insert name");
				TextField prezime = new TextField("");
				prezime.setBackground(background);
				prezime.setStyle("-fx-text-fill: white;");
				prezime.setMaxHeight(40);
				prezime.setMinHeight(40);
				prezime.setPrefHeight(Region.USE_COMPUTED_SIZE);
				prezime.setPromptText("Insert surname");
				TextField pol = new TextField("");
				pol.setBackground(background);
				pol.setStyle("-fx-text-fill: white;");
				pol.setMaxHeight(40);
				pol.setMinHeight(40);
				pol.setPrefHeight(Region.USE_COMPUTED_SIZE);
				pol.setPromptText("Insert gender(1 for male, 0 for female)");
				TextField korIme = new TextField("");
				korIme.setBackground(background);
				korIme.setStyle("-fx-text-fill: white;");
				korIme.setMaxHeight(40);
				korIme.setMinHeight(40);
				korIme.setPrefHeight(Region.USE_COMPUTED_SIZE);
				korIme.setPromptText("Insert username");
				TextField mejl = new TextField("");
				mejl.setBackground(background);
				mejl.setStyle("-fx-text-fill: white;");
				mejl.setMaxHeight(40);
				mejl.setMinHeight(40);
				mejl.setPrefHeight(Region.USE_COMPUTED_SIZE);
				mejl.setPromptText("Insert e-mail");
				Button potvrdi = new Button("Confirm");
				potvrdi.setPrefWidth(120);
				potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				Label greska = new Label("");
				greska.setTextFill(Color.WHITE);
				potvrdi.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(ime.getText().equals("") || prezime.getText().equals("") || pol.getText().equals("") || korIme.getText().equals("") || mejl.getText().equals(""))
							greska.setText("Please fill all fields");
						else if(!(pol.getText().equals("0") || pol.getText().equals("1")))
							greska.setText("Gender is invalid. Please try again.");
						else if(PristupniPodaci.getPristupniPodaci(korIme.getText())!=null) {
							greska.setText("This username already exists. Please try again.");
						}
						else if(PristupniPodaci.postojiMejl(mejl.getText())) {
							greska.setText("This e-mail already exists. Please try again.");
						}
						else if(!(mejl.getText().contains("@gmail.com") || mejl.getText().contains("@yahoo.com") || mejl.getText().contains("@student.pmf.unibl.org") || mejl.getText().contains("@pmf.unibl.org") || mejl.getText().contains("@hotmail.com") || mejl.getText().contains("@os.sveti.sava.org") || mejl.getText().contains("@os.ppnjegos.org")))
							greska.setText("E-mail invalid. Please try again.");
						else {
							osmi.getChildren().clear();
							Label rez = new Label("You have successfully added new student " + ime.getText() + " " + prezime.getText() + " to database. \nConfirmation e-mail has been sent to " + mejl.getText() + ".");
							rez.setTextFill(Color.WHITE);
							try {
								konekcija.noviPristupniPodaci(korIme.getText(), mejl.getText());
								konekcija.noviUcenik(ime.getText(), prezime.getText(), Integer.parseInt(pol.getText()), PristupniPodaci.getPristupniPodaci(korIme.getText()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							try {
								//JavaMail.sendMail(mejl.getText(), "Hey " + p.getIme() + " " + p.getPrezime() + ", \n\nYou have successfully added new student " + ime.getText() + " " + prezime.getText() + " to database."
								//		+ "\nAccess data is: \nUsername: " + korIme.getText() + "\nPassword: " + korIme.getText() + "123" + "\nE-mail: " + mejl.getText());
								JavaMail.sendMail("sara.majkic1998@gmail.com", "Hey " + p.getIme() + " " + p.getPrezime() + ", \n\nYou have successfully added new student " + ime.getText() + " " + prezime.getText() + " to database."
										+ "\nAccess data is: \nUsername: " + korIme.getText() + "\nPassword: " + korIme.getText() + "123" + "\nE-mail: " + mejl.getText());
							} catch (Exception e) {
								e.printStackTrace();
							}
							osmi.getChildren().add(rez);
						}
					}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									potvrdi.setEffect(new Glow());
								}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					          public void handle(MouseEvent e) {
					        	  potvrdi.setEffect(null);
					          }
				});
				osmi.getChildren().addAll(l, ime, prezime, pol, korIme, mejl, potvrdi, greska);
			}
		});
		ucenik.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							ucenik.setEffect(new Glow());
						}
		});
		ucenik.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            ucenik.setEffect(null);
			          }
		});
		
		//dodavanje novog predmeta
		Button predmet = new Button("Add new subject");
		predmet.setPrefWidth(120);
		predmet.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		predmet.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				osmi.getChildren().clear();
				CornerRadii corn = new CornerRadii(15);
				Background background = new Background(new BackgroundFill(Color.BLACK, corn, Insets.EMPTY));
				Label l = new Label("Please fill information for a new subject: ");
				l.setTextFill(Color.WHITE);
				TextField imePr = new TextField("");
				imePr.setBackground(background);
				imePr.setStyle("-fx-text-fill: white;");
				imePr.setMaxHeight(40);
				imePr.setMinHeight(40);
				imePr.setPrefHeight(Region.USE_COMPUTED_SIZE);
				imePr.setPromptText("Insert subject name");
				TextField raz = new TextField("");
				raz.setBackground(background);
				raz.setStyle("-fx-text-fill: white;");
				raz.setMaxHeight(40);
				raz.setMinHeight(40);
				raz.setPrefHeight(Region.USE_COMPUTED_SIZE);
				raz.setPromptText("Insert grade");
				Button potvrdi = new Button("Confirm");
				potvrdi.setPrefWidth(120);
				potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				Label greska = new Label("");
				greska.setTextFill(Color.WHITE);
				osmi.getChildren().addAll(l, imePr, raz, potvrdi, greska);
				potvrdi.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(imePr.getText().equals("") || raz.getText().equals(""))
							greska.setText("Please fill all fields.");
						else if(Predmet.postojiPredmet(imePr.getText(), Integer.parseInt(raz.getText())))
							greska.setText("Inserted subject already exists. Please try again.");
						else {
							osmi.getChildren().clear();
							Label rezultat = new Label("You have successfully added new subject " + imePr.getText() + " to database.");
							rezultat.setTextFill(Color.WHITE);
							konekcija.noviPredmet(imePr.getText(), Integer.parseInt(raz.getText()));
							osmi.getChildren().add(rezultat);
						}
					}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									potvrdi.setEffect(new Glow());
								}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					          public void handle(MouseEvent e) {
					        	  potvrdi.setEffect(null);
					          }
				});
			}
		});
		predmet.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							predmet.setEffect(new Glow());
						}
		});
		predmet.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            predmet.setEffect(null);
			          }
		});
		
		//dodavanje nove skole
		Button skola = new Button("Add new school");
		skola.setPrefWidth(120);
		skola.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		skola.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				osmi.getChildren().clear();
				CornerRadii corn = new CornerRadii(15);
				Background background = new Background(new BackgroundFill(Color.BLACK, corn, Insets.EMPTY));
				Label l = new Label("Please fill information for a new school: ");
				l.setTextFill(Color.WHITE);
				TextField imeS = new TextField("");
				imeS.setBackground(background);
				imeS.setStyle("-fx-text-fill: white;");
				imeS.setMaxHeight(40);
				imeS.setMinHeight(40);
				imeS.setPrefHeight(Region.USE_COMPUTED_SIZE);
				imeS.setPromptText("Insert school name");
				TextField grad = new TextField("");
				grad.setBackground(background);
				grad.setStyle("-fx-text-fill: white;");
				grad.setMaxHeight(40);
				grad.setMinHeight(40);
				grad.setPrefHeight(Region.USE_COMPUTED_SIZE);
				grad.setPromptText("Insert city");
				TextField mjesto = new TextField("");
				mjesto.setBackground(background);
				mjesto.setStyle("-fx-text-fill: white;");
				mjesto.setMaxHeight(40);
				mjesto.setMinHeight(40);
				mjesto.setPrefHeight(Region.USE_COMPUTED_SIZE);
				mjesto.setPromptText("Insert place");
				TextField drzava = new TextField("");
				drzava.setBackground(background);
				drzava.setStyle("-fx-text-fill: white;");
				drzava.setMaxHeight(40);
				drzava.setMinHeight(40);
				drzava.setPrefHeight(Region.USE_COMPUTED_SIZE);
				drzava.setPromptText("Insert state");
				Button potvrdi = new Button("Confirm");
				potvrdi.setPrefWidth(120);
				potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				Label greska = new Label("");
				greska.setTextFill(Color.WHITE);
				osmi.getChildren().addAll(l, imeS, grad, mjesto, drzava, potvrdi, greska);
				potvrdi.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(imeS.getText().equals("") || grad.getText().equals("") || mjesto.getText().equals("") || drzava.getText().equals(""))
							greska.setText("Please fill all fields.");
						else if(Skola.postojiSkola(imeS.getText(), grad.getText(), mjesto.getText(), drzava.getText())) 
							greska.setText("Inserted school already exists. Please try again.");
						else {
							osmi.getChildren().clear();
							Label rezultat = new Label("You have successfully added new school " + imeS.getText() + " to database.");
							rezultat.setTextFill(Color.WHITE);
							konekcija.novaSkola(imeS.getText(), grad.getText(), mjesto.getText(), drzava.getText());
							osmi.getChildren().add(rezultat);
						}
					}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									potvrdi.setEffect(new Glow());
								}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					          public void handle(MouseEvent e) {
					        	  potvrdi.setEffect(null);
					          }
				});
			}
		});
		skola.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							skola.setEffect(new Glow());
						}
		});
		skola.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            skola.setEffect(null);
			          }
		});
		
		//dodavanje novog profesora
		Button prof = new Button("Add new professor");
		prof.setPrefWidth(120);
		prof.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		prof.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				osmi.getChildren().clear();
				CornerRadii corn = new CornerRadii(15);
				Background background = new Background(new BackgroundFill(Color.BLACK, corn, Insets.EMPTY));
				Label l = new Label("Please fill information for a new professor: ");
				l.setTextFill(Color.WHITE);
				TextField ime = new TextField("");
				ime.setBackground(background);
				ime.setStyle("-fx-text-fill: white;");
				ime.setMaxHeight(40);
				ime.setMinHeight(40);
				ime.setPrefHeight(Region.USE_COMPUTED_SIZE);
				ime.setPromptText("Insert name");
				TextField prezime = new TextField("");
				prezime.setBackground(background);
				prezime.setStyle("-fx-text-fill: white;");
				prezime.setMaxHeight(40);
				prezime.setMinHeight(40);
				prezime.setPrefHeight(Region.USE_COMPUTED_SIZE);
				prezime.setPromptText("Insert surname");
				TextField pol = new TextField("");
				pol.setBackground(background);
				pol.setStyle("-fx-text-fill: white;");
				pol.setMaxHeight(40);
				pol.setMinHeight(40);
				pol.setPrefHeight(Region.USE_COMPUTED_SIZE);
				pol.setPromptText("Insert gender(1 for male, 0 for female)");
				TextField korIme = new TextField("");
				korIme.setBackground(background);
				korIme.setStyle("-fx-text-fill: white;");
				korIme.setMaxHeight(40);
				korIme.setMinHeight(40);
				korIme.setPrefHeight(Region.USE_COMPUTED_SIZE);
				korIme.setPromptText("Insert username");
				TextField mejl = new TextField("");
				mejl.setBackground(background);
				mejl.setStyle("-fx-text-fill: white;");
				mejl.setMaxHeight(40);
				mejl.setMinHeight(40);
				mejl.setPrefHeight(Region.USE_COMPUTED_SIZE);
				mejl.setPromptText("Insert e-mail");
				Button potvrdi = new Button("Confirm");
				potvrdi.setPrefWidth(120);
				potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				Label greska = new Label("");
				greska.setTextFill(Color.WHITE);
				potvrdi.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(ime.getText().equals("") || prezime.getText().equals("") || pol.getText().equals("") || korIme.getText().equals("") || mejl.getText().equals(""))
							greska.setText("Please fill al fields");
						else if(!(pol.getText().equals("0") || pol.getText().equals("1")))
							greska.setText("Gender is invalid. Please try again.");
						else if(PristupniPodaci.getPristupniPodaci(korIme.getText())!=null) {
							greska.setText("This username already exists. Please try again.");
						}
						else if(PristupniPodaci.postojiMejl(mejl.getText())) {
							greska.setText("This e-mail already exists. Please try again.");
						}
						else if(!(mejl.getText().contains("@gmail.com") || mejl.getText().contains("@yahoo.com") || mejl.getText().contains("@student.pmf.unibl.org") || mejl.getText().contains("@pmf.unibl.org") || mejl.getText().contains("@hotmail.com") || mejl.getText().contains("@os.sveti.sava.org") || mejl.getText().contains("@os.ppnjegos.org")))
							greska.setText("E-mail invalid. Please try again.");
						else {
							osmi.getChildren().clear();
							Label rez = new Label("You have successfully added new professor " + ime.getText() + " " + prezime.getText() + " to database. \nConfirmation e-mail has been sent to " + mejl.getText() + ".");
							rez.setTextFill(Color.WHITE);
							try {
								konekcija.noviPristupniPodaci(korIme.getText(), mejl.getText());
								konekcija.noviProfesor(ime.getText(), prezime.getText(), Integer.parseInt(pol.getText()), PristupniPodaci.getPristupniPodaci(korIme.getText()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							try {
								//JavaMail.sendMail(mejl.getText(), "Hey " + p.getIme() + " " + p.getPrezime() + ", \n\nYou have successfully added new professor " + ime.getText() + " " + prezime.getText() + " to database."
									//	+ "\nAccess data is: \nUsername: " + korIme.getText() + "\nPassword: " + korIme.getText() + "123" + "\nE-mail: " + mejl.getText());
								JavaMail.sendMail("sara.majkic1998@gmail.com", "Hey " + p.getIme() + " " + p.getPrezime() + ", \n\nYou have successfully added new professor " + ime.getText() + " " + prezime.getText() + " to database."
										+ "\nAccess data is: \nUsername: " + korIme.getText() + "\nPassword: " + korIme.getText() + "123" + "\nE-mail: " + mejl.getText());
							} catch (Exception e) {
								e.printStackTrace();
							}
							osmi.getChildren().add(rez);
						}
					}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
								public void handle(MouseEvent e) {
									potvrdi.setEffect(new Glow());
								}
				});
				potvrdi.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					          public void handle(MouseEvent e) {
					        	  potvrdi.setEffect(null);
					          }
				});
				osmi.getChildren().addAll(l, ime, prezime, pol, korIme, mejl, potvrdi, greska);
			}
		});
		prof.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							prof.setEffect(new Glow());
						}
		});
		prof.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            prof.setEffect(null);
			          }
		});
		
		//dodavanje novog zaduzenja
		Button zaduzenje = new Button("New responsibility");
		zaduzenje.setPrefWidth(120);
		zaduzenje.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		zaduzenje.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				osmi.getChildren().clear();
				Label objasnjenje = new Label("Please click on subject you wish to add as your new responsibility.");
				objasnjenje.setTextFill(Color.WHITE);
				osmi.getChildren().add(objasnjenje);
				for(Skola s : Skola.getSveSkole())
					for(Predmet predmet: p.getMoguciPredmeti(s)) {
						Button pre = new Button(predmet.getNaziv() + " " + predmet.getRazred() + ", " + s.getNaziv() + ", " + s.getMjesto() + ", " + s.getGrad());
						pre.setPrefWidth(350);
						pre.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
						pre.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
								//for(Predmet pr: p.getPredmeti(s))
								konekcija.novoZaduzenjeProfesora(predmet, s, p);
								Label l = new Label("Responsibility added for subject " + predmet.getNaziv() + " " + predmet.getRazred() + ", " + s.getNaziv());
								l.setTextFill(Color.WHITE);
								osmi.getChildren().clear();
								osmi.getChildren().add(l);
							}
						});
						pre.addEventHandler(MouseEvent.MOUSE_ENTERED,
								new EventHandler<MouseEvent>() {
										public void handle(MouseEvent e) {
											pre.setEffect(new Glow());
										}
						});
						pre.addEventHandler(MouseEvent.MOUSE_EXITED,
								new EventHandler<MouseEvent>() {
							          public void handle(MouseEvent e) {
							        	  pre.setEffect(null);
							          }
						});
						osmi.getChildren().add(pre);
					}
			}
		});
		zaduzenje.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							zaduzenje.setEffect(new Glow());
						}
		});
		zaduzenje.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			        	  zaduzenje.setEffect(null);
			          }
		});
		
		Button sakrij2 = new Button("Hide adding box");
		sakrij2.setPrefWidth(120);
		sakrij2.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		sakrij2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				osmi.getChildren().clear();
			}
		});
		sakrij2.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							sakrij2.setEffect(new Glow());
						}
		});
		sakrij2.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			        	  sakrij2.setEffect(null);
			          }
		});
		sedmi.getChildren().addAll(separator4, ucenik, predmet, skola, prof, zaduzenje, sakrij2);
		
		VBox deveti = new VBox(5);    //deveti je za dugmice promjene sifre
		VBox deseti = new VBox(5);    //deseti je box mijenjanja sifre
		Separator separator5 = new Separator();
		separator5.setStyle("-fx-background-color: indigo;");
		separator5.setOpacity(0.5);
		separator5.setPadding(new Insets(0, 0, 10, 0));
		Label sifra = new Label("Change password");
		sifra.setTextFill(Color.WHITE);
		sifra.setMaxWidth(Double.MAX_VALUE);
		sifra.setAlignment(Pos.CENTER);
		sifra.setPadding(new Insets(0, 0, 10, 0));
		sifra.setFont(new Font("Arial", 16));
		Button promjenaSifre = new Button("Password box");
		promjenaSifre.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		promjenaSifre.setPrefWidth(120);
		promjenaSifre.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							promjenaSifre.setEffect(new Glow());
						}
		});
		promjenaSifre.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            promjenaSifre.setEffect(null);
			          }
		});
		promjenaSifre.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				CornerRadii corn = new CornerRadii(15);
				Background background = new Background(new BackgroundFill(Color.BLACK, corn, Insets.EMPTY));
				PasswordField pf = new PasswordField();
				pf.setPromptText("Insert current password");
				pf.setBackground(background);
				pf.setStyle("-fx-text-fill: white;");
				pf.setMaxHeight(40);
				pf.setMinHeight(40);
				pf.setPrefHeight(Region.USE_COMPUTED_SIZE);
				CheckBox checkBox = new CheckBox("Show password");
				checkBox.setStyle("-fx-text-fill: white;");
				checkBox.setPadding(new Insets(0, 0, 5, 5));
				TextField textField = new TextField();
				textField.setBackground(background);
				textField.setStyle("-fx-text-fill: white;");
				textField.setPromptText("Insert current password");
				textField.setMaxHeight(40);
				textField.setMinHeight(40);
				textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
				textField.setManaged(false);
				textField.setVisible(false);
				textField.managedProperty().bind(checkBox.selectedProperty());
				textField.visibleProperty().bind(checkBox.selectedProperty());
				pf.managedProperty().bind(checkBox.selectedProperty().not());
				pf.visibleProperty().bind(checkBox.selectedProperty().not());
				textField.textProperty().bindBidirectional(pf.textProperty());
				PasswordField pf2 = new PasswordField();
				pf2.setPromptText("Insert new password");
				pf2.setBackground(background);
				pf2.setStyle("-fx-text-fill: white;");
				pf2.setMaxHeight(40);
				pf2.setMinHeight(40);
				pf2.setPrefHeight(Region.USE_COMPUTED_SIZE);
				CheckBox checkBox2 = new CheckBox("Show password");
				checkBox2.setStyle("-fx-text-fill: white;");
				checkBox2.setPadding(new Insets(0, 0, 5, 5));
				TextField textField2 = new TextField();
				textField2.setBackground(background);
				textField2.setStyle("-fx-text-fill: white;");
				textField2.setPromptText("Insert new password");
				textField2.setMaxHeight(40);
				textField2.setMinHeight(40);
				textField2.setPrefHeight(Region.USE_COMPUTED_SIZE);
				textField2.setManaged(false);
				textField2.setVisible(false);
				textField2.managedProperty().bind(checkBox2.selectedProperty());
				textField2.visibleProperty().bind(checkBox2.selectedProperty());
				pf2.managedProperty().bind(checkBox2.selectedProperty().not());
				pf2.visibleProperty().bind(checkBox2.selectedProperty().not());
				textField2.textProperty().bindBidirectional(pf2.textProperty());
				PasswordField pf3 = new PasswordField();
				pf3.setPromptText("Repeat new password");
				pf3.setBackground(background);
				pf3.setStyle("-fx-text-fill: white;");
				pf3.setMaxHeight(40);
				pf3.setMinHeight(40);
				pf3.setPrefHeight(Region.USE_COMPUTED_SIZE);
				CheckBox checkBox3 = new CheckBox("Show password");
				checkBox3.setStyle("-fx-text-fill: white;");
				checkBox3.setPadding(new Insets(0, 0, 5, 5));
				TextField textField3 = new TextField();
				textField3.setBackground(background);
				textField3.setStyle("-fx-text-fill: white;");
				textField3.setPromptText("Repeat new password");
				textField3.setMaxHeight(40);
				textField3.setMinHeight(40);
				textField3.setPrefHeight(Region.USE_COMPUTED_SIZE);
				textField3.setManaged(false);
				textField3.setVisible(false);
				textField3.managedProperty().bind(checkBox3.selectedProperty());
				textField3.visibleProperty().bind(checkBox3.selectedProperty());
				pf3.managedProperty().bind(checkBox3.selectedProperty().not());
				pf3.visibleProperty().bind(checkBox3.selectedProperty().not());
				textField3.textProperty().bindBidirectional(pf3.textProperty());
				TextField mejl = new TextField("");
				mejl.setBackground(background);
				mejl.setStyle("-fx-text-fill: white;");
				mejl.setMaxHeight(40);
				mejl.setMinHeight(40);
				mejl.setPrefHeight(Region.USE_COMPUTED_SIZE);
				mejl.setPromptText("Insert your e-mail");
				Label rez = new Label("");
				rez.setTextFill(Color.WHITE);
				Label gr = new Label("");
				gr.setTextFill(Color.WHITE);
				HBox hbpotvrdi = new HBox(5);
				hbpotvrdi.setPadding(new Insets(10, 0, 10, 0));
				Button potvrdi = new Button("Confirm");
				potvrdi.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
				hbpotvrdi.getChildren().addAll(potvrdi, gr);
				potvrdi.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(pf.getText().trim().equals("") || pf2.getText().equals("") || pf3.getText().equals("") || mejl.getText().trim().equals("")) {
							gr.setText("Please fill all fields.");
						} else
							try {
								if(!LoginKontroler.Login(p.getPristupniPodaci().getKorisnickoIme(), pf.getText().trim(), konekcija))
									gr.setText("Current password is wrong. Please try again.");
								else if(!pf2.getText().trim().equals(pf3.getText().trim()))
									gr.setText("New passwords don't match. Please try again.");
								else if(!(mejl.getText().contains("@gmail.com") || mejl.getText().contains("@yahoo.com") || mejl.getText().contains("@student.pmf.unibl.org") || mejl.getText().contains("@pmf.unibl.org") || mejl.getText().contains("@hotmail.com") || mejl.getText().contains("@os.sveti.sava.org") || mejl.getText().contains("@os.ppnjegos.org")))
									gr.setText("E-mail invalid. Please try again.");
								else {
									//gr.setText("");
									deseti.getChildren().clear();
									JavaMail.sendMail(mejl.getText(), "Hey " + p.getIme() + " " + p.getPrezime() + ", \n you have successfully changed your password to " + pf2.getText());
									//u.getPristupniPodaci().setSifra();
									konekcija.setSifra(p.getPristupniPodaci().getKorisnickoIme(), pf2.getText());
									rez.setText("You have successfully changed your password. " +"\nConfirmation e-mail has been sent to " + mejl.getText() + ".");
									deseti.getChildren().add(rez);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
					}
				});
				deseti.getChildren().addAll(pf, textField, checkBox, pf2, textField2, checkBox2, pf3, textField3, checkBox3, mejl, hbpotvrdi);
			}
		});
		deveti.getChildren().addAll(separator5, sifra, promjenaSifre);
		Button sakrij3 = new Button("Hide password box");
		sakrij3.setOnAction(e -> deseti.getChildren().clear());
		sakrij3.setPrefWidth(120);
		sakrij3.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		sakrij3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				deseti.getChildren().clear();
			}
		});
		sakrij3.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							sakrij3.setEffect(new Glow());
						}
		});
		sakrij3.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            sakrij3.setEffect(null);
			          }
		});
		deveti.getChildren().add(sakrij3);
		down.getChildren().addAll(separator, op, prvi, drugi, treci, cetvrti, peti, sesti, sedmi, osmi, deveti, deseti);
		
		VBox last = new VBox(10);
		HBox hb = new HBox(10);
		hb.setPadding(new Insets(0, 0, 0, 100));
		Separator separator6 = new Separator();
		separator6.setStyle("-fx-background-color: indigo;");
		separator6.setOpacity(0.5);
		separator6.setPadding(new Insets(0, 0, 10, 0));
		Button odjava = new Button("Log out");
		odjava.setStyle("-fx-background-color: blueviolet; -fx-text-fill: white;");
		odjava.setEffect(new Reflection());
		odjava.setMaxHeight(30);
		odjava.setMinHeight(30);
		odjava.setPrefHeight(Region.USE_COMPUTED_SIZE);
		odjava.setMaxWidth(Double.MAX_VALUE);
		odjava.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							odjava.setEffect(new Glow());
						}
		});
		odjava.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			          public void handle(MouseEvent e) {
			            odjava.setEffect(new Reflection());
			          }
		});
		odjava.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
				alert.setHeaderText("Are you sure you want to log out");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
				    stage3.close();
				    Stage primaryStage = new Stage();
				    try {
						start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		ImageView imageView = new ImageView();
		Image image = new Image(new FileInputStream("D:\\profesor.png"));
		imageView.setImage(image);
		imageView.setFitHeight(350); 
	    imageView.setFitWidth(190);   
	    

	    hb.getChildren().addAll(imageView, odjava);
		last.getChildren().addAll(separator6, hb);
		
		root.getChildren().addAll(top, down, last);
		root.setStyle("-fx-background-color: rgb(45, 33, 70);");
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				stage3.setX(event.getScreenX() - xOffset);
				stage3.setY(event.getScreenY() - yOffset);
			}
			
		});
		scrollPane.setContent(root);
		scrollPane.setPannable(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setStyle("-fx-background-color: rgb(45,33,70);");
		Scene profesor = new Scene(scrollPane, 500, 500);
		stage3.initStyle(StageStyle.TRANSPARENT);
		//stage3.setTitle("Dnevnik profesora");
		stage3.setScene(profesor);
		stage3.show();
	}

	public static void main(String[] args) {
		try {
			launch(args);
			/*LocalDate dateBefore = LocalDate.of(2017, Month.MAY, 24);
			LocalDate dateAfter = LocalDate.of(2017, Month.JULY, 29);
			System.out.println(Ucenik.razlikaUDanima(dateAfter, dateBefore));  //vraca -66 jer je prvi parametar tj. datum noviji      */
			/*String s = "sara.majkic1998@student.pmf.unibl.org";
			if(!(s.contains("@gmail.com") || s.contains("@yahoo.com") || s.contains("@student.pmf.unibl.org") || s.contains("@pmf.unibl.org") || s.contains("@hotmail.com")))
				System.out.println(false);
			else
				System.out.println(true);*/
		//	JavaMail.sendMail("sara.majkic@student.pmf.unibl.org", "Second try");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
