// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.operation.*;

public class ExampleOperation extends Operation {

    // can this operator grab the control of a system from other operators.
    // 0 for background default operation, like xbox controller driving.
    // negative for unspecified
    // operations with the same level can overwrite eachother
    public int priority = 1;
    // a list of operations to be ignored. They will be overwriten regardless of the
    // priority
    public String priority_ignores[] = {"TheClassNameToIgnore"};

    // properties for the operation. These can be set when the operation is called.
    public int prop1;
    public double prop2;
    public String prop3;

    public static boolean poll(Context context) {
        return true;
    }

    public OpState invoke(Context context) {
        // invoke is called to initialize the operation.

        this.report(ReportType.MESSAGE, "some text to send"); // report a warning, error, or message
        
        // start new operations
        ExampleOperation new_op = new ExampleOperation();
        new_op.prop1 = 10; // customize properties
        this.runOperation(new_op); // make a child operation. the priority will be max(this, other).
        // will return when the operation is done.

        context.manager.runOperation(new ExampleOperation()); // start a root operation. This will be execute once, but
        // will not wait to finish

        return this.execute(context);
    }

    public OpState execute(Context context) {
        // do the operation

        // return FINISHED to end the operation
        return OpState.FINISHED;

        // return RUNNING to continue the operation (execute will be called again)
        // return OperationState.RUNNING;

        // return CANCELED to cancel the operation (due to control conflict, wrong mode,
        // etc.)
        // return OperationState.CANCELED;
    }
}
