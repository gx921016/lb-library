package com.lonelybanana.lblibrary.log;


public class LBStackTraceUtil {

    public  static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrace,String ignorePackage,int maxDepth){
        return cropStackTrace(getRealStackTrack(stackTrace,ignorePackage) , maxDepth);
    }
    /**
     * 获取除忽略包名以外的堆栈信息
     */
    private static  StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTrace,String ignorePackage){
        int ignoreDepth = 0;
        int allDepth = stackTrace.length;
        String className;
        for (int i = allDepth-1;i>=0;i--){
            className = stackTrace[i].getClassName();
            if (ignorePackage!=null&&className.startsWith(ignorePackage)){
                ignoreDepth = i+1;
                break;
            }
        }
        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new  StackTraceElement[realDepth];
        System.arraycopy(stackTrace,ignoreDepth,realStack,0,realDepth);
        return realStack;
    }
    /**
     * 裁剪堆栈信息
     * @param callStack
     * @param maxDepth
     * @return
     */
    private  static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth){

    int realDepth = callStack.length;
    if (maxDepth>0){
        realDepth = Math.min(maxDepth,realDepth);
    }
    StackTraceElement[] realStack = new StackTraceElement[realDepth];
    System.arraycopy(callStack,0,realStack,0,realDepth);
    return realStack;
}

}
