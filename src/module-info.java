/**
 * 
 */
/**
 * 
 */
module QueensCornerGalery {
	requires java.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires com.google.gson;

	exports co.edu.uptcSoft.logic;
	exports co.edu.uptcSoft.model;
	exports co.edu.uptcSoft.view;

	opens co.edu.uptcSoft.logic to com.google.gson; // Open the package co.edu.uptcSoft.logic to com.google.gson
	opens co.edu.uptcSoft.model to com.google.gson; // Open the package co.edu.uptcSoft.model to com.google.gson

	requires java.desktop;
	requires java.xml;
}

