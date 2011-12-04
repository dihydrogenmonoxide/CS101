package boids;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/*
 * Avoid collisions
 */
public class BoidRuleSeparationView extends BoidRuleView implements AdjustmentListener {

	private BoidRuleSeparation rule;
	
	private Scrollbar strengthSlider;
	private Label     strengthValue;
	private Scrollbar falloffSlider;
	private Label     falloffValue;
	
	final double numberScale = 100000;
	
	BoidRuleSeparationView(BoidRuleSeparation rule) {
		this.rule = rule;		
		
		setLayout(new GridLayout(2,3));
		
		strengthValue = new Label("       ");		
		strengthSlider = new Scrollbar(Scrollbar.HORIZONTAL, (int) (rule.getStrength()*numberScale), 10, 1, (int) (1.0*numberScale));
		strengthSlider.addAdjustmentListener(this);
		falloffValue = new Label("       ");		
		falloffSlider = new Scrollbar(Scrollbar.HORIZONTAL, (int) (rule.getFalloff()*numberScale), 10, 0, (int) (0.5*numberScale));
		falloffSlider.addAdjustmentListener(this);
		
		add(new Label("Separation Strength: "));
		add(strengthSlider);
		add(strengthValue);
		add(new Label("Separation Falloff: "));
		add(falloffSlider);
		add(falloffValue);
		
		adjustmentValueChanged(null);
	}
	
	public void adjustmentValueChanged(AdjustmentEvent e) {
	    rule.setStrength(strengthSlider.getValue()/numberScale);
	    rule.setFalloff(falloffSlider.getValue()/numberScale);
	    strengthValue.setText("" + rule.getStrength());
	    falloffValue.setText("" + rule.getFalloff());
	}
	
	
}
