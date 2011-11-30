package boids;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class BoidRuleCohesionView extends BoidRuleView implements AdjustmentListener {
	
	
	private BoidRuleCohesion rule;
	
	private Scrollbar factorSlider;
	private Label     value;

	final double numberScale = 100000;
	
	BoidRuleCohesionView(BoidRuleCohesion rule) {
		this.rule = rule;		
		
		setLayout(new GridLayout(1,3));
		
		value = new Label("       ");		
		factorSlider = new Scrollbar(Scrollbar.HORIZONTAL, (int) (rule.getFactor()*numberScale), 10, 0, (int) (0.5*numberScale));
		factorSlider.addAdjustmentListener(this);
		
		add(new Label("Cohesion: "));
		add(factorSlider);
		add(value);

		adjustmentValueChanged(null);
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
	    rule.setFactor(factorSlider.getValue()/numberScale);
	    value.setText("" + rule.getFactor());
	}
	
	
}
