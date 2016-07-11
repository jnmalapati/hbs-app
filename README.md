# hbs-app

**************************************************************************

	                   Hierarchical buddy system ( HBS)

**************************************************************************

NOTE:
-----

Hiearchical buddy system uses the reviewers-and-reviewees.txt data file


Data File Format:

	File Name : reviewers-and-reviewees.txt  
	Format    : <<X>> reviews <<Y>> 
			    where X is a reviewer and Y is a reviewee

Note: 
	1. There should be one only reivewer for each reviewee
	2. One Reviewer can have multiple reviewees.


How to Execute hbs application ?

	If you are running Mac or Linux, just open a Terminal window and
	To run it, just run the script named "hbs.sh"

	To run it, just use the command
	                 ./hbs.sh
	                 
	If you are running Windows, just open a DOS command window and
	 Run the script named "hbs.bat."  

	To run it, just use the command
	                  hbs.bat

RESULTS :

For sample data - reviewers-and-reviewees.txt

	Jyo Malapati reviews Lakshmi Malapati
	Lakshmi Malapati reviews Sajith Malapati
	Sajith Malapati reviews Alvin
	Sean reviews Jyo Malapati
	Jyo Malapati reviews Roger Chick
	Roger Chick reviews Sri
	Sean reviews Jorge
	Jorge reviews Steve

It outputs the below: 

├── Sean
│   ├── Jyo Malapati
│   │   ├── Lakshmi Malapati
│   │   │   └── Sajith Malapati
│   │   │       └── Alvin
│   │   └── Roger Chick
│   │       └── Sri
│   └── Jorge
│       └── Steve




