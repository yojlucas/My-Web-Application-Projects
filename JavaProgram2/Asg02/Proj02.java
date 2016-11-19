/*File Proj02.java
Copyright 2014, R.G.Baldwin
Rev 03/05/14
****************************************************/

class Proj02{
//DO NOT MODIFY THE CODE IN THIS FILE

  public static void main(String[] args){
    Proj02Runner obj = new Proj02Runner();
    //Get website from command-line arg if any.
    if(args.length==0){
      //Use default web site
      obj.run("http://www.austincc.edu/baldwin");
    }else{
      obj.run(args[0]);
    }//end else
  }//end main
  //-----------------------------------------------//
}//end class Proj02
//=================================================//
