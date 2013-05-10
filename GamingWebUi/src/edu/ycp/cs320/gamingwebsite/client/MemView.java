package edu.ycp.cs320.gamingwebsite.client;

import java.util.ArrayList;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Image;
import edu.ycp.cs320.gamingwebsite.shared.*;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.NumberLabel;

public class MemView extends Composite {
		private MemDeck deck;
		private	ArrayList<String> newdeck; 
		private double click;
		private Image[] allImages; 
		private Image image;
		private Image image_1;
		private Image image_2;
		private Image image_3;
		private Image image_4;
		private Image image_5;
		private Image image_6;
		private Image image_7;
		private Image image_8;
		private Image image_9;
		private Image image_10;
		private Image image_11;
		private Image image_12;
		private Image image_13;
		private Image image_14;
		private Image image_15;
		private Image image_16;
		private Image image_17;
		private Image image_18;
		private Image image_19;
		private int pairsGone;
		private InlineLabel WinLabel;
		private Button pg;
		private NumberLabel<Double> scorelabl;
		private double score; 
		private LayoutPanel layoutPanel_1;
		private Button btnBackToHome;
		private String username;
		private Timer timer;
	
	public MemView(String username) {
	
		this.username = username;
		this.score = 0;  
		
		layoutPanel_1 = new LayoutPanel();
		initWidget(layoutPanel_1);
		
		layoutPanel_1.setSize("1033px", "617px");
		
		// winning label to the game.
		WinLabel = new InlineLabel("CONGRATULATIONS! YOU WON!");
		WinLabel.setStyleName("BigMeassage");
		WinLabel.setDirectionEstimator(true);
		WinLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layoutPanel_1.add(WinLabel);
		layoutPanel_1.setWidgetLeftWidth(WinLabel, 167.0, Unit.PX, 488.0, Unit.PX);
		layoutPanel_1.setWidgetTopHeight(WinLabel, 118.0, Unit.PX, 94.0, Unit.PX);
		
		// play again button
		pg = new Button("Play again button");
		pg.addClickHandler(new ClickHandler() {	
			public void onClick(ClickEvent event) {
				makeDeck(layoutPanel_1);
			}
		});
		
		pg.setStyleName("Playagain_button");
		pg.setText("Play again?");
		layoutPanel_1.add(pg);
		layoutPanel_1.setWidgetRightWidth(pg, 540.0, Unit.PX, 118.0, Unit.PX);
		layoutPanel_1.setWidgetTopHeight(pg, 292.0, Unit.PX, 58.0, Unit.PX);
		
		scorelabl = new NumberLabel<Double>();
		scorelabl.setStyleName("score");
		layoutPanel_1.add(scorelabl);
		layoutPanel_1.setWidgetLeftWidth(scorelabl, 322.0, Unit.PX, 161.0, Unit.PX);
		layoutPanel_1.setWidgetTopHeight(scorelabl, 218.0, Unit.PX, 45.0, Unit.PX);
		
		makeDeck(layoutPanel_1);
		
		btnBackToHome = new Button("Back to Home Screen");
		btnBackToHome.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				goHome();
			}
		});
		btnBackToHome.setStyleName("label");
		layoutPanel_1.add(btnBackToHome);
		layoutPanel_1.setWidgetLeftWidth(btnBackToHome, 779.0, Unit.PX, 107.0, Unit.PX);
		layoutPanel_1.setWidgetTopHeight(btnBackToHome, 0.0, Unit.PX, 83.0, Unit.PX);
	}
	
	
	public void makeDeck(LayoutPanel layoutPanel){												// so that it can remake the deck and reset the game
		this.deck = new MemDeck(); 																				
		this.newdeck = new ArrayList<String>(); 												
		
		deck.resetImgShow();																	// this will initialize all 20 images to the gwt and place them evenally
		
		this.pairsGone = 0;									
		
		this.image = new Image();																// this will initialize all 20 images to the gwt and place them evenally
		this.image_1 = new Image();
		this.image_2 = new Image();
		this.image_3 = new Image();
		this.image_4 = new Image();
		this.image_5 = new Image();
		this.image_6 = new Image();
		this.image_7 = new Image();
		this.image_8 = new Image();
		this.image_9 = new Image();
		this.image_10 = new Image();
		this.image_11 = new Image();
		this.image_12 = new Image();
		this.image_13 = new Image();
		this.image_14 = new Image();
		this.image_15 = new Image();
		this.image_16 = new Image();
		this.image_17 = new Image();
		this.image_18 = new Image();
		this.image_19 = new Image();
		
		this.allImages = new Image[]{
				image,
				image_1,
				image_2,
				image_3,
				image_4,
				image_5,
				image_6,
				image_7,
				image_8,
				image_9,
				image_10,
				image_11,
				image_12,
				image_13,
				image_14,
				image_15,
				image_16,
				image_17,
				image_18,
				image_19,
		};
		
		for (int i = 0; i < allImages.length; i++) {
			Image img = allImages[i];
			
			final int imageNum = i;

			img.addClickHandler(new ClickHandler() {												// add click handler to image		
				@Override
				public void onClick(ClickEvent event) {
					deck.setImgshow(imageNum, 1);
					click++; 
					update();
				}
			});
			
			layoutPanel.add(img);																	// add to panel
			img.setVisible(true);
			
			int row = i / 5;																		// set position/size
			int col = i % 5;
			
			layoutPanel.setWidgetLeftWidth(img, 75.0 + col*125.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(img, 25.0 + row*175.0, Unit.PX, 200.0, Unit.PX);
		}
		render();
		update();
	}

	/**
	 * This updates the game state based on what the user does
	 */
	public void update() {
		
		for(int i =0 ; i<allImages.length; i++){													//give each image object the address so it will display
			if(deck.getImgshow().get(i) == 1){														//if imgshow is 1, it should show an image
				allImages[i].setUrl(newdeck.get(i));
			}
			else{
				allImages[i].setUrl("CardImage/Backcard.jpg");										//imgshow is 0, so the back card should show
			}
		}
		
		CardsShown();
		
		if(IsFinished()){																			// if the game is than create the score and set the score in the database
			score = (click/2) *100 ;
			setscore(); 
			
			timer = new Timer() {																	// creating a timer to shows the score and button to try again.
				@Override
				public void run() {
					scorelabl.setValue(score);
					scorelabl.setVisible(true);
					WinLabel.setVisible(true);
					pg.setVisible(true);
					
				}
			};

			timer.schedule(500);
		}
		else{
			scorelabl.setVisible(false);
			WinLabel.setVisible(false);
			pg.setVisible(false);
		}
	}
	/**
	 * This method makes a newdeck by taking the values from the deck class and 
	 * putting their file names in a separate array
	 */
	public void render(){
		
		deck.make();													//make two decks of memcards and store in a new array
		score = 0; 
		click = 0;
		pairsGone = 0;
		
		String img1;
		//make decks of memcards and store in a new array
			
		for(int i = 0; i<deck.getMemDeck().size(); i++){
			//newdeck is used to store the addresses of each element of memdeck so that is can be printed in the GWT
			//new deck is not shuffled and should be represented the same way as the deck. 
			if(deck.getCard(i) == Images.Star) {
				img1 = "CardImage/star1.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}
			else if(deck.getCard(i) == Images.Circle){
				img1 = "CardImage/circle.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}
			else if(deck.getCard(i) == Images.Square){
				img1 = "CardImage/square1.jpg";	
				newdeck.add(img1); //add the correct string to the deck

			}
			else if(deck.getCard(i) == Images.Triangle){
				img1 = "CardImage/triangle.jpg";
				newdeck.add(img1); //add the correct string to the deck

			}
			else if(deck.getCard(i) == Images.Arrow){
				img1 = "CardImage/arrow.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}

			else if(deck.getCard(i) == Images.Speech){
				img1 = "CardImage/speech.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}
			else if(deck.getCard(i) == Images.Hexagon){
				img1 = "CardImage/hexagon.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}

			else if(deck.getCard(i) == Images.Light){
				img1 = "CardImage/light.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}

			else if(deck.getCard(i) == Images.Heart){
				img1 = "CardImage/heart.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}	
			
			else if(deck.getCard(i) == Images.fourPStar){
				img1 = "CardImage/fourpstar.jpg";
				newdeck.add(img1); //add the correct string to the deck
			}
		}
	}
	
	/**
	 * This method determines which cards are showing
	 */
	public void CardsShown(){
		
		boolean samecards = false;													 	//if they match, set them invisible
		Images img1 = null, img2 = null;
		int imgindex1 = 0, imgindex2=0;
		
		if (click%2 == 0){
			 
			imgindex1 = deck.getImgshow().indexOf(1); 
			imgindex2 = deck.getImgshow().lastIndexOf(1);
			
			if ((imgindex1 != -1 && imgindex2 != -1) && (imgindex1 != imgindex2)){
				img1 = deck.getCard(imgindex1);
				img2 = deck.getCard(imgindex2); 
				
				samecards = deck.isSame(img1, img2);
				
				final int hideIndex1 = imgindex1;
				final int hideIndex2 = imgindex2;
				
				if(samecards == true){
					timer = new Timer() {
						@Override
						public void run() {
							allImages[hideIndex1].setVisible(false);
							allImages[hideIndex2].setVisible(false);
						}
					};
					timer.schedule(500);
					
					pairsGone++; 		
					
				}
			}
				
			deck.resetImgShow();														//flip the cards back over
		}
	}
	
	public boolean IsFinished(){														// to see if the game is finished
		if(pairsGone == (deck.getMemDeck().size()/2)){
			return true;
		}
		else{
			return false; 
		}
	}
	public void goHome(){																// goes back to the mainworld
		MainWorld main = new MainWorld(username);
		layoutPanel_1.clear();
		layoutPanel_1.add(main);
		timer.cancel();
		main.update();
	}
	
	protected void setscore() {															// RPC call to server to see if username/password is valid
		RPC.loginService.setscore(username, score, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				System.out.println("Success (should go to home page)" );
			}
			
			@Override
			public void onFailure(Throwable caught) {									//  display error (e.g., in a label)
				System.out.println("Error logging in (could not contact server)");
			}
		});
	}
}
