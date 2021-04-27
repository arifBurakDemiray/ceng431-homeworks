package controller;

import observation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Login;
import model.User;
import view.HomeView;
import view.TopRateView;
import view.user.CollectionView;
import view.user.DiscoverUsersView;
import view.user.FollowerView;
import view.user.FollowingView;
public class HomeController {

	private Observer view;
	private Login model;
	
	public HomeController(Observable model, Observer view) {
		this.view=view;
		this.model=(Login) model;
		this.model.addObserver(view);
		((HomeView)view).addLogoutButtonListener(new LogoutButtonListener());
		((HomeView)view).addFollowingButtonListener(new FollowingButtonListener());
		((HomeView)view).addFollowerButtonListener(new FollowerButtonListener());
		((HomeView)view).addDiscoverUsersButtonListener(new DiscoverUsersButtonListener());
		((HomeView)view).addTopRateButtonListener(new TopRateButtonListener());
		((HomeView)view).addCollectionButtonListener(new CollectionButtonListener());
	}
	
	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setUser(null);	
		}
		
	}
	
	class FollowingButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView)view).setVisible(false);
			FollowingView followingView = new FollowingView((User)model.getUser());
			FollowingController viewController = new FollowingController(model.getUser(),followingView);
			
			
		}
		
	}
	
	class FollowerButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView)view).setVisible(false);
			FollowerView followerView = new FollowerView((User)model.getUser());
			FollowerController viewController = new FollowerController(model.getUser(),followerView);
			
			
		}
		
	}
	
	class DiscoverUsersButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView)view).setVisible(false);
			DiscoverUsersView discoverUsersView = new DiscoverUsersView((User)model.getUser());
			DiscoverUsersController viewController = new DiscoverUsersController(model.getUser(),discoverUsersView);
			
			
		}
		
	}
	
	class TopRateButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView)view).setVisible(false);
			final Observable rates = RateHelper.initializeRateModel();
			rates.addObserver(view);
			TopRateView topView = new TopRateView(rates);
			TopRateController topController = new TopRateController(rates,topView);
		}
		
	}
	
	class CollectionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			((HomeView)view).setVisible(false);
			CollectionView collectionView = new CollectionView((User)model.getUser());
			CollectionController collectionController = new CollectionController(model.getUser(),collectionView);
			
		}
		
	}
	
}
