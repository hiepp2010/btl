package application;

import java.util.ArrayList;

public class Question {
   String name,text,choice1,choice2,choice3,choice4,choice5;
   Double grade1,grade2,grade3,grade4,grade5;
   ArrayList<String> choice = new ArrayList();
   ArrayList<Double> grade = new ArrayList();
   int type=0;
   Question(String name, String text ,String choice1,Double grade1,String choice2,Double grade2,String choice3,Double grade3,String choice4,Double grade4,String choice5,Double grade5)
   {
	   this.name=name;
	   this.text=text;
	   this.choice1=choice1;
	   this.grade1=grade1;
	   this.choice2=choice2;
	   this.grade2=grade2;
	   this.choice3=choice3;
	   this.grade3=grade3;
	   this.choice4=choice4;
	   this.grade4=grade4;
	   this.choice5=choice5;
	   this.grade5=grade5;
	   if(!choice1.isEmpty())
	   {
		   if(grade1!=0) this.type++;
		   this.choice.add(choice1);
		   this.grade.add(grade1/100);
		   if(!choice2.isEmpty())
		   {
			   if(grade2!=0) this.type++;
			   this.choice.add(choice2);
			   this.grade.add(grade2/100);
			   if(!choice3.isEmpty())
			   {
				   if(grade3!=0) this.type++;
				   this.choice.add(choice3);
				   this.grade.add(grade3/100);
				   if(!choice4.isEmpty())
				   {
					   if(grade4!=0) this.type++;
					   this.choice.add(choice4);
					   this.grade.add(grade4/100);
					   if(!choice5.isEmpty())
					   {
						   if(grade5!=0) this.type++;
						   this.choice.add(choice5);
						   this.grade.add(grade5/100);
						   
					   }   
				   }
			   }
			   
		   }
	   }
   }
   
}
