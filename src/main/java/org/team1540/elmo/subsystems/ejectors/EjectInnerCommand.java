package org.team1540.elmo.subsystems.ejectors;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.elmo.utils.WaitDashboardVarCommand;

public class EjectInnerCommand extends SequentialCommandGroup {

    public EjectInnerCommand(Ejectors.InnerEjector innerEjector) {
        addRequirements(innerEjector);

        addCommands(
                new InstantCommand(innerEjector::extend),
                new WaitDashboardVarCommand(innerEjector,"push",0.15), // wait for suction to fully unsucc. // TODO: Test without waiting
                new InstantCommand(()->innerEjector.setSuction(false))
        );
    }

}
