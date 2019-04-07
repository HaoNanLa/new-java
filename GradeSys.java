import java.util.Scanner;
/**
*	项目：成绩管理系统
*	@author Gates
*/
public class GradeSys{
	public static void main(String[] args) {
		ResourceMachine rm = new ResourceMachine();
		//获取用户操作
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("请输入要执行的操作：");
			int op = input.nextInt();
			switch(op){
				//1.录入学生，课程，成绩单
				case 1:
					register(rm,input);
					break;
				//2.选课
				case 2:
					slect(rm,input);
					break;
				//3.查询某个学生的平均成绩
				case 3:
					check(rm,input);
					break;
				//4.查询某门课的平均成绩
				case 4:
					average(rm,input);
					break;
				//5.求某门课的最好成绩
				case 5:
					best(rm,input);
					break;
				//6.某种课程的排名
				case 6:
					sort(rm,input);
					break;
				//7.退出系统
				case 7:
					System.out.println("Welcome to come back.");
					System.exit(0);
					break;
			}
		}
	}
	static void register(ResourceMachine rm, Scanner input){
		System.out.println(" 1.学生\n 2.课程\n 3.成绩单\n");
		System.out.println("请输入要录入的内容:");
		int op = input.nextInt();
		int n = 0;
		int sn = 0;
		int cn = 0;
		String name = null;

		switch(op){
			case 1:
				System.out.println("请输入录入学生的学号:");
				n = input.nextInt();
				System.out.println("请输入录入学生的姓名:");
				name = input.next();
				rm.addStudent(new Student(n,name));
				break;
			case 2:
				System.out.println("请输入录入课程的编号:");
				n = input.nextInt();
				System.out.println("请输入录入课程的名字:");
				name = input.next();
				System.out.println("请输入录入课程的描述:");
				String description = input.next();
				rm.addCourse(new Course(n,name,description));
				break;
			case 3:
				System.out.println("请输入录入学生的学号:");
				sn = input.nextInt();
				System.out.println("请输入录入课程的编号:");
				cn = input.nextInt();
				System.out.println("请输入录入成绩:");
				n = input.nextInt();
				rm.addGrade(new Grade(sn,cn,n));
				break;
		}
	}

	static void slect(ResourceMachine rm, Scanner input){
		
		System.out.println("请输入要选课的学生学号：");
		int sn = input.nextInt();
		System.out.println("请输入要选课的课程编号：");
		int cn = input.nextInt();
		rm.selectCourse(sn,cn);
	}
	//3.查询某个学生的平均成绩
	static void check(ResourceMachine rm, Scanner input){
		System.out.println("请输入要查询的学生学号：");
		int sn = input.nextInt();
		rm.check(sn);
	}
	//4.查询某门课的平均成绩
	static void average(ResourceMachine rm, Scanner input){
		System.out.println("请输入要查询的课程编号：");
		int cn = input.nextInt();
		rm.average(cn);
	}
	//5.求某门课的最好成绩
	static void best(ResourceMachine rm, Scanner input){
		System.out.println("请输入要查询的课程编号：");
		int cn = input.nextInt();
		rm.best(cn);
	}
	//6.某种课程的排名
	static void sort(ResourceMachine rm, Scanner input){
		System.out.println("请输入要查询的课程编号：");
		int cn = input.nextInt();
		rm.sort(cn);
	}
}

//-- 学生类
class Student{
	//-- 成员变量
	int stuNo;								//-- 学号
	String name;							//-- 名字
	Course[]  courses = new Course[100];	//-- 课程
	int courseNo;							//-- 课程数
	//-- 构造函数
	Student(){}		//-- 缺省构造器
	Student(int stuNo, String name){
		this.stuNo = stuNo;
		this.name = name;
	}
	//-- 成员方法
	int getCourseNo(){	//-- 看自己有几门课程
		return this.courseNo;
	}
	void addCourse(Course course){	//-- 添加课程
		this.courses[courseNo++] = course; 
	}
	void removeCourse(int courseNo){  //-- 根据课程编号删除课程
		for(int i=0;i<this.courseNo;i++){
			if(courses[i].courseNo == courseNo){
				for(int j=i;j<this.courseNo;j++){
					courses[j] = courses[j+1];
				}
				this.courseNo--;
				break;
			}
		}
	}
	void show(){
		System.out.println("[学号:"+stuNo+" 姓名:"+name+" 课程: {");
		for(int i=0;i<courseNo;i++){
			System.out.print("  ");
			courses[i].show();
		}
		System.out.println("}]");
	}
}

//-- 课程类
class Course{
	//-- 成员变量
	int courseNo;							//-- 课程编号
	String courseName;				//-- 课程名字
	String description = "";			//-- 课程简介
	
	//-- 构造方法
	Course(){}
	Course(int courseNo, String courseName){
		this.courseNo = courseNo;
		this.courseName = courseName;
	}
	Course(int courseNo, String courseName, String description){
		this.courseNo = courseNo;
		this.courseName = courseName;
		this.description = description;
	}

	//-- 成员方法
	void show(){
		System.out.println(
		"[ 编号:"+this.courseNo + 
		" 课程名:"+this.courseName+ 
		" 描述:"+this.description+"]");
	}
}

class Grade{
	int stuNo;
	int courseNo;
	double value;
	Grade(){}
	Grade(int stuNo, int courseNo, double value){
		this.stuNo = stuNo;
		this.courseNo = courseNo;
		this.value = value;
	}
}

class ResourceMachine{
	Student[] stus = new Student[1000];
	Course[] cs = new Course[1000];
	Grade[] gs = new Grade[1000]; 
	int stusNo;
	int csNo;
	int gsNo;
	ResourceMachine(){
		showUI();
		
		stus[stusNo++] = new Student(1,"吕布");
		stus[stusNo++] = new Student(2,"张飞");
		stus[stusNo++] = new Student(3,"关羽");
		stus[stusNo++] = new Student(4,"成独秀");
		stus[stusNo++] = new Student(5,"刘备");
		
		cs[csNo++] = new Course(1,"Java编程思想","java语言的经典教材");
		cs[csNo++] = new Course(2,"量子力学","电子和原子的运动规律");
		cs[csNo++] = new Course(3,"密码学","加密解密");
		cs[csNo++] = new Course(4,"天体物理","宇宙");
		cs[csNo++] = new Course(5,"移动通信","通信原理");
		cs[csNo++] = new Course(6,"经济管理","money");
		
		gs[gsNo++] = new Grade(1,1,85);
		gs[gsNo++] = new Grade(1,2,20);
		gs[gsNo++] = new Grade(1,3,1);
		gs[gsNo++] = new Grade(2,1,60);
		gs[gsNo++] = new Grade(2,2,90);
		gs[gsNo++] = new Grade(2,3,80);
		gs[gsNo++] = new Grade(2,4,39);
		gs[gsNo++] = new Grade(3,1,40);
		gs[gsNo++] = new Grade(3,2,99);
		gs[gsNo++] = new Grade(3,3,18);
	}

	void showUI(){
		System.out.println("-------成绩统计系统------");
		System.out.println("1.录入学生，课程，成绩单");
		System.out.println("2.选课");
		System.out.println("3.查询某个学生的平均成绩");
		System.out.println("4.查询某门课的平均成绩");
		System.out.println("5.求某门课的最好成绩");
		System.out.println("6.某种课程的排名");
		System.out.println("7.退出系统");
		System.out.println("-------成绩统计系统------");
	}

	//
	void selectCourse(int stuNo, int courseNo){
		Student stu = this.getStudent(stuNo);
		Course c = getCourse(courseNo);
		if(stu!=null &&  c!=null){
			stu.addCourse(c);
		}
	}

	Student getStudent(int stuNo){
		for(int i=0;i<stusNo;i++)
			if (stus[i].stuNo == stuNo)
				return stus[i];
		return null;
	}
	Course getCourse(int courseNo){
		for(int i=0;i<csNo;i++)
			if (cs[i].courseNo == courseNo)
				return cs[i];
		return null;
	}
	void addStudent(Student stu){
		stus[stusNo++] = stu;
	}
	void addCourse(Course c){
		cs[csNo++] = c;
	}
	void addGrade(Grade g){
		gs[gsNo++] = g;
	}

	void findAllGrades(int stuNo){
		for(int i=0;i<gsNo;i++){
			if(gs[i].stuNo == stuNo){
				Course c = getCourse(gs[i].courseNo);
				System.out.println(c.courseName+":"+gs[i].value);
			}
		}
	}
	//查询某个学生的平均成绩
	void check(int sn){
		double sum = 0;
		int number = 0;
		for(int i=0;i<gsNo;i++){
			if(gs[i].stuNo == sn){
				sum += gs[i].value;
				number++;
			}
		}
		sum /= number;
		System.out.println("该生的平均成绩是:"+sum);
	}
	//查询某门课的平均成绩
	void average(int cn){
		double sum = 0;
		int number = 0;
		for(int i=0;i<gsNo;i++){
			if(gs[i].courseNo == cn){
				sum += gs[i].value;
				number++;
			}
		}
		sum /= number;
		System.out.println("该门课程的平均成绩是:"+sum);
	}
	void best(int cn){
		double max = 0;
		for(int i=0;i<gsNo;i++){
			if(gs[i].courseNo == cn){
				max = (max>gs[i].value) ? max : gs[i].value);
			}
		}
		System.out.println("该门课程的最好成绩是:"+max);
	}
	//某种课程的排名
	void sort(int cn){
		Grade[] cgs = new Grade[1000]; //用来存放编号cn的成绩单
		int cgsn = 0;				//成绩单个数
		for(int i=0;i<gsNo;i++){
			if(gs[i].courseNo == cn){
				cgs[cgsn++] = gs[i];
			}
		}

		//把stu[i]的（名次-1）存在s[i]里
		int[] s = new int[cgsn];
		for(int i=0;i<cgsn;i++){
			for(int j=0;j<cgsn;j++){
				if(i!=j&&cgs[j].value>cgs[i].value){
					s[i]++;
				}
			}
		}
		System.out.println("排序后的成绩是：");
		for(int i=0;i<cgsn;i++){
			for(int j=0;j<cgsn;j++){
				if(s[j]==i){
					String str = "";
					str += "成绩第" + (i+1) + "名的";
					str += "成绩为：" + cgs[j].value;
					System.out.println(str);				
				}
			}
		}
	}
}

//1.界面UI
//2.录入学生，课程，成绩单
//3.可选课
//4.可查询某个学生的平均成绩
//5.查询某门课的平均成绩
//6.求某门课的最好成绩
//7.某种课程的排名
//8.退出系统


