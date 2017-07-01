//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.text.DefaultCaret;

import org.omg.PortableInterceptor.LOCATION_FORWARD;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		
		ExecutableFileClass os=new ExecutableFileClass(code, param);
		
		
		
		
		return os.run();
		
	}
	
	
	static class ExecutableFileClass{
		private final char stackPtrAdd = '>';
		private final char stackPtrSub = '<';
		private final char stackAdd = '+';
		private final char stackSub = '-';
		private final char output = '.';
		private final char input = ',';
		private final char loopFront = '[';
		private final char loopback = ']';
		
		int codePtr =0;
		HashMap<Integer, Integer> loopMark= new HashMap<>();
		ArrayList<Integer> stack = new ArrayList<>();
		int stackPtr =0;
		String param ="";
		String code = "";
		int paramPtr = 0;
		String outString ="";
		public ExecutableFileClass(String code, String param){
			this.param = param;
			this.code=code;
			for(int i=code.length()-1;i>=0;i--){
				out:if(code.charAt(i)==loopback){
					int mark = 0;
				
					for(int j=i-1;j>=0;j--){
						if(code.charAt(j)==loopback){
							mark++;
						}
						if(code.charAt(j)==loopFront){
							if(mark==0){
								loopMark.put(i, j);
								break out;
							}else{
								mark--;
							}
						}
					}
				}
			}
			
			
			
		}
		public String run(){
			while(codePtr<code.length()){
				stepForRun(code.charAt(codePtr));
				
			}
			
		
			
			
			
			return outString;
			
		}
//		ArrayList<Character> stack = new ArrayList<>();
//		int stackPtr =0;
//		String param ="";
//		String code = "";
//		int paramPtr = 0;
//		String outString ="";
		private final static String  newline  = "\n";
		public String oneStepRun(){
			if(codePtr<code.length()){
				stepForRun(code.charAt(codePtr));
			}
			String stackString ="栈内容:"+ stack.toString();
			String stackPtrString = "当前运行到栈位置:"+(stackPtr+1);
			String codePtrString = "当前运行到代码位置:"+ (codePtr+1);
			String outString ="目前输出:"+ this.outString;
			String paramPtrString = "运行到参数位置:"+(paramPtr+1);
			
			
			
			
			return outString+newline+stackPtrString+newline+stackString
					+newline+codePtrString+newline+paramPtrString;
			
			
		}
		private void stepForRun(char c){
			
			
			switch (c){
			case stackPtrAdd : 
				stackPtr++;
				codePtr++;
				break;
			case stackPtrSub : 
				stackPtr--;
				codePtr++;
				break;
			case stackAdd : 
				if(stack.size()-1<stackPtr){
					stack.add(stackPtr, (1));
					
				}else{
					stack.set(stackPtr, (stack.get(stackPtr)+1));

				}
				codePtr++;
				break;
			case stackSub : 
				if(stack.size()-1<stackPtr){
					stack.add(stackPtr, (0));
				}else{
					stack.set(stackPtr,  (stack.get(stackPtr)-1));
				}
				codePtr++;
				break;
			case output : 
				outString=outString+(char)((int)stack.get(stackPtr));
//				System.out.println("stackPtr:"+(int)stack.get(stackPtr));
//				System.out.println((int)'7');
				codePtr++;
				break;
			case input : 
				if(stack.size()<=stackPtr){
					
					stack.add(stackPtr, (int)param.charAt(paramPtr));
//				System.out.println("stackPtr:"+(int)stack.get(stackPtr));
				}else{
					
					stack.set(stackPtr, (int)param.charAt(paramPtr));
				}
				paramPtr++;
				codePtr++;
				
				break;
			case loopFront : 
				codePtr++;
				break;
			case loopback : 
			
				
				if(stack.get(stackPtr)==0){
//					for(int i=0;i<stack.size();i++){
////					System.out.println("~!!!!!!!!"+(int)stack.get(i));
//					}
////					System.out.println();
					codePtr++;
				}else{
					codePtr=loopMark.get((Integer)codePtr);
				}
				break;
			default :
				codePtr++;
			}
			
				
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args){
		 
				
			      

	}
}
