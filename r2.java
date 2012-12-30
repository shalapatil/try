import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class  Parameter
{
	public String type;
	public String name;
}


class Function
{
	public String type;
	public String name;
	public String access;
	public boolean static1;
	public boolean final1;	
	public boolean const1;
	public Vector<Parameter> parameter=new Vector<Parameter>();

	
	public Function()
	{
		access="default";		
	}
	
	public void print_function_details()
	{
		System.out.println("\nFunction name :: "+name);
		System.out.println("Data Type :: "+type);
		System.out.println("Access specifier :: "+access);
		System.out.println("Static ? :: "+static1);
		System.out.println("Final ? :: "+final1);
		System.out.println("Const ? : "+const1);
		
		for(int i=0;i<parameter.size();i++)
		{
			System.out.print(parameter.get(i).type+"  "+parameter.get(i).name+" , ");			
			
		}
				
	}
}

class Variable
{
	public String type;
	public String name;
	public String access;
	public boolean static1;
	public boolean final1;
	public boolean abstract1;
	public boolean const1;
	
	public Variable()
	{
		access="default";
		
	}
	
	public void print_variable_details()
	{
		System.out.println("\nVariable name :: "+name);
		System.out.println("Data Type :: "+type);
		System.out.println("Access specifier :: "+access);
		
		System.out.println("Static ? :: "+static1);
		System.out.println("Final ? :: "+final1);
		System.out.println("Abstract ? : "+abstract1);
		System.out.println("Const ? : "+const1);
				
	}
	
}

class output
{
	
	public String classname;
	public boolean static_class;
	public boolean final_class;
	public boolean abstract_class;
	public String access_class;
	public String parent_class;
	public Vector<String> interfac=new Vector<String>();
	public Vector<Variable> variable=new Vector<Variable>();
	public Vector<Function> function=new Vector<Function>();

	public output() 
	{
		//parent_class="java.lang";
		
	}
	
	public void print_class_details()
	{
		
		System.out.println("*** Class Details ******");
	
		System.out.println("\nClass name :: "+classname);
		System.out.println("Static class :: "+static_class);
		System.out.println("Final class :: "+final_class);
		System.out.println("Abstract class :: "+abstract_class);
		System.out.println("Access specifier of class :: "+access_class);
		System.out.println("Parent class : "+parent_class);
		System.out.print("Interfaces implemented : ");
		for(int i=0;i<interfac.size();i++)
		{
			System.out.print(interfac.get(i)+" , ");
			
		}
		System.out.println("\n\n**** Instance variable Details ****** ");
		
		Variable v;
		for(int i=0;i<variable.size();i++)
		{
			v=variable.get(i);
			v.print_variable_details();
		}
		
		Function f;
		for(int i=0;i<function.size();i++)
		{
			f=function.get(i);
			f.print_function_details();
		}
		
	}
	
}



public class r1
{
	
	static output op[]= new output[5];
	
    private static String class1 = "class";
    private static String class_name = "[a-zA-Z0-9_$]*";
    private static String datatype = " boolean| char| byte| short| int| long| float| double| void";
    private static String extra="public|private|protected|abstract|final|static|const"; 
    private static String class_end = "}";
    private static String extend ="extends";
    private static String implement ="implements";
    private static String any ="[a-z A-Z0-9_$\\.,\\*]*";
    
   private static String name = "[a-zA-Z0-9_$\\.\\*]*";
   private static String is_fun = "\\(";
   private static String end_fun = "\\)";
  
   private static String block_start = "\\{";
   private static String block_end = "\\}";
    
    static int pos=0;
    static int end=0;
    
    private static String INPUT = "public static final class foo "+//extends java.util.* implements actionlistener" +
    		"						  { private int x1,x2"+
    		"							 public const float b ;" +
    		"							 int m ; char t;" +
    		"							 public void prinnt(int a,int b){" +
    		"								 int p ; char rt  ;" +
    		"								}" +
    		"							 int z ;" +
    		"" +
    		
    		"						 } " +
    		"					private class boo implements abc,xyz" +
    		"						 { " +
    		"							 static char y; " +
    		"							 public void display()" +
    		"							{ " +
    		"								   double c ;  " +
    		"								    char x ;	" +
    		"							 }" +
    		"							 float g ; " +
    		"						}";
    private static String REPLACE = "object";

    public static void main(String[] args) {
       
       Pattern p_class = Pattern.compile(class1);
       Pattern p_class_name = Pattern.compile(class_name);
       Pattern p_class_obj = Pattern.compile(class_name);       
       Pattern p_extra= Pattern.compile(extra);
       Pattern p_datatype = Pattern.compile(datatype);
       Pattern p_is_fun = Pattern.compile(is_fun);
       Pattern p_end_fun = Pattern.compile(end_fun);       
       Pattern p_name = Pattern.compile(name);
       Pattern p_extend=Pattern.compile(extend);
       Pattern p_implement=Pattern.compile(implement);
       Pattern p_any=Pattern.compile(any);
       Pattern p_block_start=Pattern.compile(block_start);
       Pattern p_block_end=Pattern.compile(block_end);

       //  System.out.println("*** "+p_is_fun.toString());
       
       // get a matcher object
       Matcher m_class = p_class.matcher(INPUT); 
       Matcher m_class_name = p_class_name.matcher(INPUT);
       Matcher m_class_obj=p_class_obj.matcher(INPUT);
       Matcher m_extra= p_extra.matcher(INPUT);
       Matcher m_datatype = p_datatype.matcher(INPUT); 
       Matcher m_is_fun = p_is_fun.matcher(INPUT);
       Matcher m_end_fun = p_end_fun.matcher(INPUT);       
       Matcher m_name = p_name.matcher(INPUT);    
       Matcher m_any=p_any.matcher(INPUT);
       Matcher m_block_start=p_block_start.matcher(INPUT);
       Matcher m_block_end=p_block_end.matcher(INPUT);
       Matcher m_search_next_class=p_class.matcher(INPUT);
       
       //Matcher m5 = p5.matcher(INPUT);    
       
       
       int class_start=0;
       int k=-1;
       //** to find the start of class definition i.e to search for word class
       while(m_class.find())  
       {
    	   k++;
    //	   System.out.println(m_class.toString());
    //	   System.out.println(p_class_name.toString());
    	   op[k]=new output();
    	   System.out.println("=================================================================");
    	   //to find the name of the class,starts seraching with the next index found by index of m1
    	   
    	   m_extra=m_extra.region(class_start, m_class.start());
    	   
    	   while(m_extra.find())
    	   {
    		   //System.out.println(" "+m_extra.group(0));
    		   if(m_extra.group(0).matches("static"))
    			op[k].static_class=true;
    		   else if(m_extra.group(0).matches("final"))
       			op[k].final_class=true;
    		   else if(m_extra.group(0).matches("abstract"))
       			op[k].abstract_class=true;
    		   else
    			   op[k].access_class=m_extra.group(0);
 
    	   }
    	   
    	   if(m_class_name.find(m_class.end()+1))
    	   {
    		 //  System.out.println("Class name : "+m_class_name.group(0)+"  "+m_class.end());
     		   pos=m_class_name.end()+1;
     		   class_start=pos;
     		   op[k].classname=m_class_name.group(0);
     		   
     		   //m_extend=m_extend.region(pos,pos+10);
     		   if(m_any.find(pos))
     		   {
     			   pos=m_any.end();
     			   
     			 //  System.out.println("oops "+m_any.group(0));
     			  Matcher m_extend = p_extend.matcher(m_any.group(0));    
     		      Matcher m_implement = p_implement.matcher(m_any.group(0));
     		      m_name= p_name.matcher(m_any.group(0));
     		      
     		      if(m_extend.find())
     		      {
     		    	  if(m_name.find(m_extend.end()+1))
     		    	  {
     		    		  System.out.println("Extends "+m_name.group(0));
     		    	  
     		    	  op[k].parent_class=m_name.group(0);
     		    	  }
     		    	  
     		      }
     		      
     		     // m_name=p_name.matcher(m_any.group(0));

     		     if(m_implement.find())
    		      {
     		    	 int t=m_implement.end()+1;
     		    	 try
     		    	 {
    		    	 while (m_name.find(t))
    		    	  {
    		    		//  System.out.println("Implements "+m_name.group(0));
    		    		  t=m_name.end()+1;
    		    		  op[k].interfac.add(m_name.group(0));
    		    	  }
     		    	 }catch(IndexOutOfBoundsException i)
     		    	 {}  	  
    		      }
     		   }
    	   }
    	   String name="";
    	   int bl_st=0,bl_en=0,nxt_class=0;
    	  
    	   if(m_block_start.find(pos))
    	   {	// System.out.println(m_block_start.start()+" ");

    		   if(m_block_start.find(m_block_start.end()))
    		   {
    			 bl_st=m_block_start.start();
    			 if(m_block_end.find(bl_st))
    				 bl_en=m_block_end.start();
    	//		 System.out.println(bl_st+" "+bl_en);
    			 
    		   }
    	   }
    	   
    	   if(m_search_next_class.find(pos))
    		   nxt_class=m_search_next_class.start();
    	   else
    		   nxt_class=-1;
    	   
    	   
    	   Boolean d=false,co = false;
    	   int ch=1;
    	   
    	   //while(d=m_datatype.find(pos) | co= m_class_obj.find(pos))
    	   do
    	   {
    		//  System.out.println("for "+m_datatype.group(0)+"start at "+m_datatype.start());
    		   d=m_datatype.find(pos);
    		   co= m_class_obj.find(pos);
    		   //if(!d)
    			 //  break;
    		     
    		  if(d && co)
    		  {
    			  if(m_datatype.start()>m_class_obj.start())
    				ch=1;
    			  else
    				  ch=2;
    		  }
    		  else if(d && !co)
    		  {
    			  ch=1;
    			  
    		  }
    		  else
    			  ch=2;
    		   
    		   if(d && ch==1)
    		   {
    		   if(d && m_datatype.start()>nxt_class  && nxt_class!=-1 )
    			   break;
    		   
    		   if(d && m_datatype.start()>bl_st && m_datatype.start()<bl_en)
    		   {
    			   pos=bl_en;
    			   if(m_block_start.find(pos))
    	    	  
    			   {	 //System.out.println(m_block_start.start()+" ");

    	    		   if(m_block_start.find(m_block_start.end()))
    	    		   {
    	    			 bl_st=m_block_start.start();
    	    			 if(m_block_end.find(bl_st))
    	    				 bl_en=m_block_end.start();
    	    		//	 System.out.println(bl_st+" "+bl_en);
    	    			 
    	    		  }
    	    		   continue;
    	    	   }
    			   
    			   continue;
    		   }
    		   
    		   if(co && ch==2)
    		   {
    		   if(co && m_class_obj.start()>nxt_class  && nxt_class!=-1 )
    			   break;
    		   
    		   if(co && m_class_obj.start()>bl_st && m_class_obj.start()<bl_en)
    		   {
    			   pos=bl_en;
    			   if(m_block_start.find(pos))
    	    	  
    			   {	 //System.out.println(m_block_start.start()+" ");

    	    		   if(m_block_start.find(m_block_start.end()))
    	    		   {
    	    			 bl_st=m_block_start.start();
    	    			 if(m_block_end.find(bl_st))
    	    				 bl_en=m_block_end.start();
    	    		//	 System.out.println(bl_st+" "+bl_en);
    	    			 
    	    		  }
    	    		   continue;
    	    	   }
    			   
    			   continue;
    		   }
    		   }
    		   try
    		   {
    		   m_extra=m_extra.region(pos, m_datatype.start());
    		   }
    		   catch(IndexOutOfBoundsException e){}
        	   
    		   Variable v = new Variable();
    		   v.type=m_datatype.group(0);
        	   while(m_extra.find())
        	   {
        		   //System.out.println(" "+m_extra.group(0));
        		   if(m_extra.group(0).matches("static"))
        			v.static1=true;
        		   else if(m_extra.group(0).matches("final"))
           			v.final1=true;
        		   else if(m_extra.group(0).matches("abstract"))
           			v.abstract1=true;
        		   else if(m_extra.group(0).matches("const"))
              			v.const1=true;
        		   else
        			   v.access=m_extra.group(0);
        	   
        	   }
        	   
        	  
    		//   System.out.print(" : "+m_datatype.group(0));
    		   pos=m_datatype.end()+1;
    		   
    		  
    		   m_any=p_any.matcher(INPUT);
    		   
    		   
    		   if(m_any.find(pos))
    		   {  name=m_any.group(0);
    		   	  
    		     m_is_fun=m_is_fun.region(m_any.end(),m_any.end()+5);
    		   	  
    		   	  if(m_is_fun.find())
    		   	  {
    		   		
    		   		System.out.println(" =: "+m_any.group(0));
    	    		System.out.println("Its function....");
	    			Function f=new Function();

    	    		if(m_end_fun.find(m_is_fun.end()))
    	    		{
        	    		//System.out.println("Its function....");
    	    			
    	    			f.access= v.access;
    	    			f.const1= v.const1;
    	    			f.final1= v.final1;
    	    			f.static1=v.static1;
    	    			f.type=   v.type;
    	    			f.name=m_any.group(0);
    	    			
    	    			
    	    			try
    	    			{
    	    				m_name=p_name.matcher(INPUT);
    	    				m_name=m_name.region(m_is_fun.end(),m_end_fun.start());
    	    			
    	    				int t=0;
    	    				Parameter p=new Parameter();
    	    				while(m_name.find())
    	    				{
    	    					
    	    					if(m_name.group(0).equals(""))
    	    						continue;
    	    					if(t==0)
    	    					{
    	    						p=new Parameter();
    	    						p.type=m_name.group(0);
    	    						t++;
    	    						//System.out.print(m_name.group(0));
    	    					}
    	    					else if(t==1)
    	    					{
    	    						t--;
    	    						p.name=m_name.group(0);
    	    						f.parameter.add(p);
    	    						//System.out.print(m_name.group(0));
    	    						System.out.println("p type "+p.type);
    	    						System.out.println("p nameme "+p.name);
    	    						//f.parameter.add(p);
    	    	    				
    	    					}
    	    						
    	    						
    	    					//System.out.println(" "+m_name.group(0));
    	    					
    	    						
    	    				}
    	    			}
    	    			catch(Exception e){}
    	    			
    	    		}
    	    		op[k].function.add(f);
    				
    		  	  }
    		   	  else
    		   	  {
    		   		m_name= p_name.matcher(m_any.group(0)); 
    		   		int t=0;
    		    	 try
    		    	 {
    		    		 while (m_name.find())
    		    		 {
    		    			 if(m_name.group(0).equals(""))
    		    				 continue;
    		    			 System.out.println("Implements "+m_name.group(0));
    		    			
    		    			 Variable v1=new Variable();
    		    			 v1.abstract1=v.abstract1;
    		    			 v1.access=v.access;
    		    			 v1.const1=v.const1;
    		    			 v1.final1=v.final1;
    		    			 v1.static1=v.static1;
    		    			 v1.type=v.type;
    		    			 
    		    			 v1.name=m_name.group(0);
    		    			 op[k].variable.add(v1);
    		       
   	   		    
    		    		 }
    		    	 }catch(IndexOutOfBoundsException i)
    		    	 {}  	  
    		   		  
    		   		
    		   		  
    		   	  }
    		   	  
    		   }  
    		   	    	//	   System.out.println(" =: "+m_name.group(0));
    		   }
     		   
    		   
    	   }
    	   while(d | co);
    	   
    	   
    	   
    	   op[k].print_class_details();
    	   
       }
    	   
       
      // System.out.println(INPUT);
   }
}
