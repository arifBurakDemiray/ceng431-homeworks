package controller;

import observation.*;
import service.TopRateService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import fileio.DatabaseResult;
import fileio.OutfitRepository;
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

public class HomeController {

	private Observer view;
	private Login model;
	private TopRateService topRateService;

	public HomeController(Observable model, Observer view) {
		this.view = view;
		this.model = (Login) model;
		this.model.addObserver(view);
		topRateService = new TopRateService();
		((HomeView) view).addLogoutButtonListener(new LogoutButtonListener());
		((HomeView) view).addFollowingButtonListener(new FollowingButtonListener());
		((HomeView) view).addFollowerButtonListener(new FollowerButtonListener());
		((HomeView) view).addDiscoverUsersButtonListener(new DiscoverUsersButtonListener());
		((HomeView) view).addTopRateButtonListener(new TopRateButtonListener());
		((HomeView) view).addCollectionButtonListener(new CollectionButtonListener());
		((HomeView)view).addSelectCollectionListener(new SelectCollectionListener());
	}

	class SelectCollectionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				String selectedItemName = ((HomeView) view).getListSelected();
				if (selectedItemName.contains("|")) {
					DatabaseResult result = null;
					result = (new OutfitRepository()).getOutfitById(selectedItemName.split("-")[1]);
					Outfit outfit = (Outfit) result.getObject();
					if(outfit!=null)
					{
						Observable outfitReview = new OutfitReview(outfit,(User) model.getUser());
						Observer outfitPopupView = new OutfitPopupView(outfitReview);
						final var pc = new OutfitPopupController(outfitReview, outfitPopupView);
					}				
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setUser(null);
			model.removeObserver(view);
		}

	}

	class FollowingButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			FollowingView followingView = new FollowingView((User) model.getUser());
			final var viewController = new FollowingController(model.getUser(), followingView);

		}

	}

	class FollowerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			FollowerView followerView = new FollowerView((User) model.getUser());
			final var viewController = new FollowerController(model.getUser(), followerView);

		}

	}

	class DiscoverUsersButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			DiscoverUsersView discoverUsersView = new DiscoverUsersView((User) model.getUser());
			final var viewController = new DiscoverUsersController(model.getUser(), discoverUsersView);

		}

	}

	class TopRateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			final Observable rates = topRateService.initializeRateModel();
			rates.addObserver(view);
			TopRateView topView = new TopRateView(rates);
			final var topController = new TopRateController(rates, topView);
		}

	}

	class CollectionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView) view).setVisible(false);
			CollectionView collectionView = new CollectionView((User) model.getUser());
			final var collectionController = new CollectionController(model.getUser(), collectionView);

		}

	}

}
