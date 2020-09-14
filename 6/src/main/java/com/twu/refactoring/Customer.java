package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		final String nextLine = "\n";
		String result = "Rental Record for " + getName() + nextLine;
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();

			// determine amounts for each line
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount = getThisAmount(thisAmount, each, 2, 2);
				break;
				case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount = getThisAmount(thisAmount, each, 1.5, 3);

			}

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			final String tableChar = "\t";
			result += tableChar + each.getMovie().getTitle() + tableChar
					+ String.valueOf(thisAmount) + nextLine;
			totalAmount += thisAmount;

		}
		// add footer lines
		final String amountOwed = "Amount owed is ";
		result += amountOwed + String.valueOf(totalAmount) + nextLine;
		final String youEarned = "You earned ";
		final String renterPoints = " frequent renter points";
		result += youEarned + String.valueOf(frequentRenterPoints)
				+ renterPoints;
		return result;
	}

	private double getThisAmount(double thisAmount, Rental each, double i, int i2) {
		thisAmount += i;
		if (each.getDaysRented() > i2)
			thisAmount += (each.getDaysRented() - i2) * 1.5;
		return thisAmount;
	}

}
