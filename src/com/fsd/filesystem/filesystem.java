package com.fsd.filesystem;

import java.io.File;
import java.util.Scanner;

public class filesystem {

	public Boolean user_exit() {
		Boolean flag = Boolean.FALSE;
		System.out.println("Do you want to continue ");
		System.out.println("Y/N");
		Scanner scr = new Scanner(System.in);
		String user_pref = scr.next();
		if (user_pref.equalsIgnoreCase("Y")) {
			flag = true;
		} else if (user_pref.equalsIgnoreCase("N")) {
			flag = false;
		} else {
			System.out.println("Please enter the valid data!!");
			flag = user_exit();
		}
		return flag;
	}

	public Boolean delete_existing_file(String File_name) {

		String Is_File_exist = get_file(File_name);

		if (Is_File_exist.equals("File Not  Found")) {

			System.out.println("File does not exist to delete it  ");
			return false;
		} else {
			File myfile = new File("C:\\Users\\Derick_George\\OneDrive - Dell Technologies\\Desktop\\File_Directery",
					File_name + ".txt");
			try {
				myfile.delete();
				System.out.println("File deleted Successfully ");
				return true;
			} catch (Exception ex) {
				System.out.println("Exception occured Exception message " + ex.getMessage());
				return false;
			}
		}

	}

	public Boolean addnewfile(String File_name) {

		String Is_File_exist = get_file(File_name);
		if (Is_File_exist.equals("File Not  Found")) {

			File myfile = new File("C:\\Users\\Derick_George\\OneDrive - Dell Technologies\\Desktop\\File_Directery",
					File_name + ".txt");
			try {
				boolean create_new_file = myfile.createNewFile();
				if (create_new_file == true) {

					System.out.println("Added new File ");
					return true;
				}

			} catch (Exception e) {
				System.out.println("Unable to add file");
			}

			return true;

		} else {

			System.out.println("File already Exists ");
			return false;
		}
	}

	public String stripExtension(String str) {
		if (str == null)
			return null;

		int pos = str.lastIndexOf(".");

		if (pos == -1)
			return str;

		return str.substring(0, pos);
	}

	public int binary_search(String[] File_list, String File_name) {

		int l = 0, r = File_list.length - 1;

		while (l <= r) {
			int m = l + (r - l) / 2;
			String orginal_file = stripExtension(File_list[m]);
			int res = File_name.compareTo(orginal_file);
			// Check if x is present at mid
			if (res == 0)
				return m; // If x greater, ignore left half

			if (res > 0)
				l = m + 1;
			else
				r = m - 1;

		}

		return -1;
	}

	public static int stringCompare(String str1, String str2) {

		int l1 = str1.length();
		int l2 = str2.length();
		int lmin = Math.min(l1, l2);

		for (int i = 0; i < lmin; i++) {
			int str1_ch = (int) str1.charAt(i);
			int str2_ch = (int) str2.charAt(i);

			if (str1_ch != str2_ch) {
				return str1_ch - str2_ch;
			}
		}

		if (l1 != l2) {
			return l1 - l2;
		}

		else {
			return 0;
		}
	}

	public void Sort_Ascending(String[] File_list) {
		String temp;
		for (int j = 0; j < File_list.length; j++) {
			for (int i = j + 1; i < File_list.length; i++) {
				// comparing adjacent strings
				if (File_list[i].compareTo(File_list[j]) > 0) {
					temp = File_list[j];
					File_list[j] = File_list[i];
					File_list[i] = temp;
				}
			}
		}
		for (int i = File_list.length - 1; i > 0; i--)
			System.out.println(File_list[i]);

	}

	public String get_file(String File_name) {

		File folder = new File("C:\\Users\\Derick_George\\OneDrive - Dell Technologies\\Desktop\\File_Directery");
		String[] File_list = folder.list();

		if (File_name.equals("")) {
			System.out.println("The List of files  in the Directory  ");

			// for (int i = 0; i < File_list.length; i++) {
			// System.out.println(File_list[i]);

			// }

			Sort_Ascending(File_list);

			return File_name;
		} else {

			int Is_file_exists = binary_search(File_list, File_name);

			if (Is_file_exists == -1) {
				System.out.println("File Entered  Not  Found ");
				return "File Not  Found";
			} else {

				System.out.println("File  Found ");
				return File_name;
			}
		}

	}

	public static void main(String arg[]) {
		int n = 0;
		filesystem test01 = new filesystem();
		System.out.print("ApplicationName:LockedMe.com");
		System.out.println("\tDeveloped By:Derick George\n");
		do {
			System.out.println("1. Add a New File ");
			System.out.println("2. Search an existing file");
			System.out.println("3. Delete a Specific File ");
			System.out.println("4. Show all files in directory ");
			System.out.println("5. Quit \n ");

			System.out.println("Please enter any Option ");
			Scanner scr = new Scanner(System.in);
			n = scr.nextInt();
			switch (n) {
			case 1:
				System.out.println("Enter File Name to add  ");
				scr = new Scanner(System.in);
				String File_Name2 = scr.next();
				boolean add_file = test01.addnewfile(File_Name2);
				Boolean flag = test01.user_exit();
				if (!flag)
					n = 5;
				break;
			case 2:
				System.out.println("Enter File Name ");
				scr = new Scanner(System.in);
				String file_Name = "";
				if (scr.hasNextLine()) {
					file_Name = scr.next();
				}

				String test = test01.get_file(file_Name);
				Boolean flag1 = test01.user_exit();
				if (!flag1)
					n = 5;
				break;
			case 3:
				System.out.println("Enter File Name to delete  ");
				scr = new Scanner(System.in);
				String File_Name3 = scr.next();
				boolean test3 = test01.delete_existing_file(File_Name3);
				Boolean flag2 = test01.user_exit();
				if (!flag2)
					n = 5;
				break;
			case 4:
				String test1 = test01.get_file("");
				Boolean flag3 = test01.user_exit();
				if (!flag3)
					n = 5;
				break;

			case 5:

				break;

			default:
				System.out.println("Wrong Choice Select any Below Choice ");
				break;

			}
		} while (n != 5);
		if (n == 5)
			System.out.println("Thank you for using : LockedMe.com ");
	}
}
