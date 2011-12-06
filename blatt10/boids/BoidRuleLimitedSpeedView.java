package boids;


import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class BoidRuleLimitedSpeedView extends BoidRuleView implements AdjustmentListener{

	BoidRuleLimitedSpeed rule;
	
	Label 		speed_lbl;
	Scrollbar 	speed_scrl;
	
	double s_fact=10000; //scaling factor
	
	public BoidRuleLimitedSpeedView(BoidRuleLimitedSpeed _rule){
		this.rule=_rule;
		setLayout(new GridLayout(1,3));
		
		speed_lbl = new Label("Speed");		
		speed_scrl = new Scrollbar(Scrollbar.HORIZONTAL, (int) (rule.getFactor()*s_fact), 10, 1, (int) (0.5*s_fact));
		speed_scrl.addAdjustmentListener(this);
		
		add(new Label("Speed: "));
		add(speed_scrl);
		add(speed_lbl);
		
		
		adjustmentValueChanged(null);
	}
	public void adjustmentValueChanged(AdjustmentEvent e) {
	    rule.setFactor(speed_scrl.getValue()/s_fact);
	    speed_lbl.setText("" + rule.getFactor());
	}
	
}
