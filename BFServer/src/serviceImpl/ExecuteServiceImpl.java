//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		int CodePtr =0;
		HashMap<Integer, Integer> loopMark= new HashMap<>();
		ArrayList<Character> stack = new ArrayList<>();
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
			while(CodePtr<code.length()){
				stepForRun(code.charAt(CodePtr));
				
			}
			
			
			
			
			
			return outString;
			
		}
		private void stepForRun(char c){
			
			
			switch (c){
			case stackPtrAdd : 
				stackPtr++;
				CodePtr++;
				break;
			case stackPtrSub : 
				stackPtr--;
				CodePtr++;
				break;
			case stackAdd : 
				if(stack.size()-1<stackPtr){
					stack.add(stackPtr, (char)(1));
					
				}else{
					stack.set(stackPtr, (char)(stack.get(stackPtr)+1));

				}
				CodePtr++;
				break;
			case stackSub : 
				if(stack.size()-1<stackPtr){
					stack.add(stackPtr, (char)(0));
				}else{
					stack.set(stackPtr, (char) (stack.get(stackPtr)-1));
				}
				CodePtr++;
				break;
			case output : 
				outString=outString+(char)stack.get(stackPtr);
//				System.out.println("stackPtr:"+(int)stack.get(stackPtr));
//				System.out.println((int)'7');
				CodePtr++;
				break;
			case input : 
				if(stack.size()<=stackPtr){
					
					stack.add(stackPtr, (char)param.charAt(paramPtr));
//				System.out.println("stackPtr:"+(int)stack.get(stackPtr));
				}else{
					
					stack.set(stackPtr, (char)param.charAt(paramPtr));
				}
				paramPtr++;
				CodePtr++;
				
				break;
			case loopFront : 
				CodePtr++;
				break;
			case loopback : 
			
				
				if(stack.get(stackPtr)==0){
//					for(int i=0;i<stack.size();i++){
////					System.out.println("~!!!!!!!!"+(int)stack.get(i));
//					}
////					System.out.println();
					CodePtr++;
				}else{
					CodePtr=loopMark.get((Integer)CodePtr);
				}
				break;
			default :
				CodePtr++;
			}
			
				
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args){
		//                                           31      	   44
		String code = ",>,,>++++++++[<------<------>>-]<<[>[>+>+<<-]>>[<<+>>-]<<<-]>>>++++++[<++++++++>-],<.>.";
		String param = "4+3 ";
//		ExecuteServiceImpl exe = new ExecuteServiceImpl();
//		try {
//			System.out.println(exe.execute(code,param));
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ExecutableFileClass os = new ExecutableFileClass(code, param);
		System.out.println(os.run());
	}
}
