package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string 
 *  
 *  @author Wiqas
 *  @version 1.2
 */
public class Person1 {
  /** Holds the persons real name */
  	private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	public Person1(String pname) {
		name = pname;
	}
	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		char oldChar;
		String newWord = "";
		for (int i = 2; i < input.length(); i++) {
			oldChar = input.charAt(i);
			newWord += oldChar;
		}
		for (int j = 0; j < 2; j++) {
			newWord += input.charAt(j);
		}
		newWord = input;
		return input;
	}
	
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */

	public String toString(String input) {
		return name + calc(input);
	}


	public static void main(String[] args) {
		Person1 p = new Person1("gtg123b");
		p.calc(p.name);
		System.out.print(p);
	}

}
