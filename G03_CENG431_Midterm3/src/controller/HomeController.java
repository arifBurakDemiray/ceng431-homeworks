package controller;

import observation.*;
import service.HomeService;
import service.TopRateService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Login;
import model.Outfit;
import model.OutfitReview;
import model.User;
import view.CollectionView;
import view.DiscoverUsersView;
import view.FollowerView;
import view.FollowingView;
import view.HomeView;
import view.OutfitPopupView;
import view.TopRateView;

/**
 * This class handles home screen requests
 */
public class HomeController extends Consumable {

	private Observer view;
	private Login model; // why login model, because when we press logout we should go back
	private TopRateService topRateService; // so login model holds user, when logout clicked
	private HomeService homeService; // user set to be null

	public HomeController(Observable model, Observer view) {
		this.view = view;
		this.model = (Login) model;
		this.model.addObserver(view);
		topRateService = new TopRateService(); // to initialise top rates
		homeService = new HomeService(); // to show up outfits
		// add button listeners
		((HomeView) view).addLogoutButtonListener(new LogoutButtonListener());
		((HomeView) view).addFollowingButtonListener(new FollowingButtonListener());
		((HomeView) view).addFollowerButtonListener(new FollowerButtonListener());
		((HomeView) view).addDiscoverUsersButtonListener(new DiscoverUsersButtonListener());
		((HomeView) view).addTopRateButtonListener(new TopRateButtonListener());
		((HomeView) view).addCollectionButtonListener(new CollectionButtonListener());
		((HomeView) view).addSelectCollectionListener(new SelectCollectionListener());
	}

	// by this class user can select one of the outfits
	class SelectCollectionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				String selectedItemName = ((HomeView) view).getListSelected();
				Outfit outfit = homeService.getSelectedOutfit(selectedItemName);
				if (outfit != null) // if outfit is found
				{ // create outfit review model
					Observable outfitReview = new OutfitReview(outfit, (User) model.getUser());
					Observer outfitPopupView = new OutfitPopupView(outfitReview);
					final Consumable outfitPopupController = new OutfitPopupController(outfitReview, outfitPopupView);
					outfitPopupController.supressNotUsed();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	// if logout button pressed
	// set model's user to null
	// and remove observer from model
	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setUser(null);
			model.removeObserver(view);
		}

	}

	// if following button pressed
	// show up following view
	class FollowingButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			FollowingView followingView = new FollowingView((User) model.getUser());
			final Consumable followingViewController = new FollowingController(model.getUser(), followingView);
			followingViewController.supressNotUsed();
		}

	}

	// if follower button pressed, show up follower view
	class FollowerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			FollowerView followerView = new FollowerView((User) model.getUser());
			final var followerViewController = new FollowerController(model.getUser(), followerView);
			followerViewController.supressNotUsed();
		}

	}

	// this class handles Discover users
	// if button pressed shows up discover users page
	class DiscoverUsersButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			DiscoverUsersView discoverUsersView = new DiscoverUsersView((User) model.getUser());
			final Consumable discoverUsersController = new DiscoverUsersController(model.getUser(), discoverUsersView);
			discoverUsersController.supressNotUsed();
		}

	}

	// this class handles top rate button actions
	// if button pressed shows up top rates
	class TopRateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			// initialise top rate model
			final Observable rates = topRateService.initializeRateModel();
			rates.addObserver(view);
			TopRateView topView = new TopRateView(rates);
			final Consumable topRateController = new TopRateController(rates, topView);
			topRateController.supressNotUsed();
		}

	}

	// this class handles collections button actions
	// if button pressed opens collections screen
	class CollectionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false); // set home not visible
			CollectionView collectionView = new CollectionView((User) model.getUser()); // create collection view
			// create collection controller
			final Consumable collectionController = new CollectionController(model.getUser(), collectionView);
			collectionController.supressNotUsed();
		}

	}

}
