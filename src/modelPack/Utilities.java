package modelPack;

public class Utilities {

 public static String editPages(String servlet,String thread_id,int recordCount){
  String pages = "<p>";
  for(int i=1; true; i++){
   pages += ("<a href=\"/bbs_system_4/"+servlet+"?");
   if(thread_id!=null){ pages += ("thread_id="+thread_id+"&"); }
    pages += ("page="+i);
    pages += ("\">"+i+"</a>");
    if(i*20>=recordCount){ break; }
     pages += " / ";
    }
    return pages+="</p>";
  }
}