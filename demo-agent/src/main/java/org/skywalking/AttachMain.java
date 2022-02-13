package org.skywalking;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.util.List;

/**
 * @Author: Gol
 */
public class AttachMain {
    
    
    public static void main(String[] args) throws Exception {

        List<VirtualMachineDescriptor> listBefore = VirtualMachine.list();
        String jar = "/Users/gaolei/Documents/project/java/linkkids/skywalking-demo/demo-agent/target/demo-agent.jar";
        VirtualMachine vm = null;
        List<VirtualMachineDescriptor> listAfter = null;
        while (true) {
            listAfter = VirtualMachine.list();
            for (VirtualMachineDescriptor vmd : listAfter) {
                if (!listBefore.contains(vmd)) {
                    vm = VirtualMachine.attach(vmd);
                    vm.loadAgent(jar);
                    vm.detach();
                    return;
                }
            }
            Thread.sleep(1000);
        }


    }

}
