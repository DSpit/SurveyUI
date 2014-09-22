


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * Main Frame class that designs the UI and implements
 * listeners to create the main body of the event driven
 * survey program.
 * 
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class SurveyUI extends JFrame{

// Constant -------------------------------------------- //
	
	public final int PADDING = 25;
	public final String NAME_1 = "Rick Titball";
	public final String NAME_2 = "Dick Weiner";
	public final String NAME_3 = "Tiny Cox";
	public final String CB_OPTION_1 = "Option 1";
	public final String CB_OPTION_2 = "Option 2";
	public final String CB_OPTION_3 = "Option 3";
	public final String RB_OPTION_1 = "Radio Option 1";
	public final String RB_OPTION_2 = "Radio Option 2";
	public final String RB_OPTION_3 = "Radio Option 3";
	
	private final String NEXT = "Next";
	private final String EXIT = "Exit";
	private final String COUNTER = " Counter: ";
	private final Dimension BUTTON_DIMEN = new Dimension(125, 35);
	
// Members --------------------------------------------- //
	
	private JPanel mNamePanel;
	private JToggleButton mName1;
	private JToggleButton mName2;
	private JToggleButton mName3;
	private ButtonGroup mBGroup;
	private JPanel mSelectionPanel;
	private JCheckBox mChBx1;
	private JCheckBox mChBx2;
	private JCheckBox mChBx3;
	private JRadioButton mRB1;
	private JRadioButton mRB2;
	private JRadioButton mRB3;
	private ButtonGroup mRBGroup;
	private JPanel mCounterPanel;
	private JLabel mName1Count;
	private JLabel mName2Count;
	private JLabel mName3Count;
	private JLabel mChBx1Count;
	private JLabel mChBx2Count;
	private JLabel mChBx3Count;
	private JLabel mRB1Count;
	private JLabel mRB2Count;
	private JLabel mRB3Count;
	private JPanel mControlPanel;
	private JButton mNext;
	private JButton mExit;
	
	
// Constructors ---------------------------------------- //
	
	/**
	 * Constructor which puts all the GUI elements together
	 * into the main frame.
	 */
	public SurveyUI(){
		
		// initial set up of Frame
		super("Survey");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//set up panels
		this.setupCounterPanel();	//must be initialized first so the labels are set properly
		this.setupNamePanel();
		this.setupSelectionPanel();
		this.setupControlPanel();
		
		//set main frame layout and add panels
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(mNamePanel, 0);
		mainPanel.add(mSelectionPanel, 1);
		mainPanel.add(mCounterPanel, 2);
		mainPanel.add(mControlPanel, 3);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, PADDING, PADDING));
		this.add(mainPanel);
		
		//final frame statements
		this.setVisible(true);
		this.pack();
	}
	
// MAIN ------------------------------------------------ //
	
	public static void main(String[] args){
		new SurveyUI();
	}
	
// Private --------------------------------------------- //
	
	/**
	 * Method used to compartmentalize code. This particular method
	 * is used to set up the main buttons of the application.
	 */
	private void setupNamePanel(){
		
		mNamePanel = new JPanel(new GridLayout(1, 3, PADDING, PADDING)); 
		mNamePanel.setBorder(new TitledBorder("Names"));
		
		//set up the buttons
		mName1 = new JToggleButton(NAME_1);
		mName2 = new JToggleButton(NAME_2);
		mName3 = new JToggleButton(NAME_3);
		
		//add the buttons to a button group
		mBGroup = new ButtonGroup();
		mBGroup.add(mName1);
		mBGroup.add(mName2);
		mBGroup.add(mName3);
		
		//add listeners to the buttons
		NameButtonListener listener = new NameButtonListener();
		mName1.addActionListener(listener);
		mName1.addItemListener(new SelectionListener(mName1Count));
		mName2.addActionListener(listener);
		mName2.addItemListener(new SelectionListener(mName2Count));
		mName3.addActionListener(listener);
		mName3.addItemListener(new SelectionListener(mName3Count));
		
		//add components to panel
		mNamePanel.add(mName1);
		mNamePanel.add(mName2);
		mNamePanel.add(mName3);
	}
	
	/**
	 * Method used to compartmentalize code. This particular method
	 * is used to set up the selection area of the application.
	 */
	private void setupSelectionPanel(){
		
		mSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
		mSelectionPanel.setBorder(new TitledBorder("Selection Panel"));
		mSelectionPanel.setVisible(false);
		
		//set up checkboxs
		mChBx1 = new JCheckBox(CB_OPTION_1);
		mChBx2 = new JCheckBox(CB_OPTION_2);
		mChBx3 = new JCheckBox(CB_OPTION_3);
		
		//set up radio buttons and their group
		mRB1 = new JRadioButton(RB_OPTION_1);
		mRB2 = new JRadioButton(RB_OPTION_2);
		mRB3 = new JRadioButton(RB_OPTION_3);
		
		//add the Radio buttons to the Button Group
		mRBGroup = new ButtonGroup();
		mRBGroup.add(mRB1);
		mRBGroup.add(mRB2);
		mRBGroup.add(mRB3);
		
		//add listeners to each of the panel components
		mChBx1.addItemListener(new SelectionListener(mChBx1Count));
		mChBx2.addItemListener(new SelectionListener(mChBx2Count));
		mChBx3.addItemListener(new SelectionListener(mChBx3Count));
		mRB1.addItemListener(new SelectionListener(mRB1Count));
		mRB2.addItemListener(new SelectionListener(mRB2Count));
		mRB3.addItemListener(new SelectionListener(mRB3Count));
		
		//add the components to the panel
		mSelectionPanel.add(mChBx1);
		mSelectionPanel.add(mChBx2);
		mSelectionPanel.add(mChBx3);
		mSelectionPanel.add(mRB1);
		mSelectionPanel.add(mRB2);
		mSelectionPanel.add(mRB3);
	}
	
	/**
	 * Method used to compartmentalize code. This particular method
	 * is used to set up the counter area of the Survey application
	 */
	private void setupCounterPanel(){
		
		mCounterPanel = new JPanel(new GridLayout(3,1));
		mCounterPanel.setBorder(new TitledBorder("Counter Panel"));
		
		//setup labels
		mName1Count = new JLabel(NAME_1 + COUNTER + 0);
		mName2Count = new JLabel(NAME_2 + COUNTER + 0);
		mName3Count = new JLabel(NAME_3 + COUNTER + 0);
		
		mChBx1Count = new JLabel(CB_OPTION_1 + COUNTER + 0);
		mChBx2Count = new JLabel(CB_OPTION_2 + COUNTER + 0);
		mChBx3Count = new JLabel(CB_OPTION_3 + COUNTER + 0);
		
		mRB1Count = new JLabel(RB_OPTION_1 + COUNTER + 0);
		mRB2Count = new JLabel(RB_OPTION_2 + COUNTER + 0);
		mRB3Count = new JLabel(RB_OPTION_3 + COUNTER + 0);
		
		//add labels to panel
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
		namePanel.add(mName1Count);
		namePanel.add(mName2Count);
		namePanel.add(mName3Count);
		JPanel chBxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
		chBxPanel.add(mChBx1Count);
		chBxPanel.add(mChBx2Count);
		chBxPanel.add(mChBx3Count);
		JPanel rBPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
		rBPanel.add(mRB1Count);
		rBPanel.add(mRB2Count);
		rBPanel.add(mRB3Count);
		
		mCounterPanel.add(namePanel, 0);
		mCounterPanel.add(chBxPanel, 1);
		mCounterPanel.add(rBPanel, 2);	
	}
	
	/**
	 * Method used to compartmentalize code. This particular method
	 * is used to set up the control panel of the application.
	 */
	private void setupControlPanel(){
		
		mControlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
		mControlPanel.setBorder(new TitledBorder("Controls"));
		
		//set up the refresh/ next button
		mNext = new JButton(NEXT);
		mNext.setPreferredSize(BUTTON_DIMEN);
		mNext.addActionListener(new ControlListener());
		
		//set up the exit button
		mExit = new JButton(EXIT);
		mExit.setPreferredSize(BUTTON_DIMEN);
		mExit.addActionListener(new ControlListener());
		
		//add components to panel
		mControlPanel.add(mNext);
		mControlPanel.add(mExit);
		
	}
	
// Listener Classes ------------------------------------ //
	
	/**
	 * listener used for the main controls of this application. Allows
	 * for resetting (next) and exiting of the program
	 */
	class ControlListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(mNext.equals(e.getSource())){
				
				//rest the name panel
				mBGroup.clearSelection();
				Enumeration<AbstractButton> bList= mBGroup.getElements();
				
				while(bList.hasMoreElements()){
					
					AbstractButton b = bList.nextElement();
					b.setEnabled(true);
				}
				
				//rest the selection panel
				mChBx1.setSelected(false);
				mChBx2.setSelected(false);
				mChBx3.setSelected(false);
				mRBGroup.clearSelection();
				
				mSelectionPanel.setVisible(false);
				
				//NOTE: labels get reset automatically because they are linked by 
				//ItemListeners which add or subtract based on if the corresponding button is selected
				
				pack();
				
			}else if(mExit.equals(e.getSource())){
				System.exit(0);				//exit application
			}
		}
		
	}
	
	/**
	 * Listener used to toggle buttons in the button group and to
	 * disable the unselected ones.
	 */
	class NameButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Enumeration<AbstractButton> bList= mBGroup.getElements();
			
			while(bList.hasMoreElements()){
				JToggleButton b = (JToggleButton) bList.nextElement();
				
				if(!(b.equals(e.getSource()))){
					b.setEnabled(false);
				}
			}
			
			mSelectionPanel.setVisible(true);
			pack();
		}
		
	}
	
	/**
	 * This Listener is used to link the labels with the corresponding
	 * items and will update the label whenever the item is selected or unselected.
	 */
	class SelectionListener implements ItemListener{
		
		private JLabel mCounterLabel;
		private int mCounter;
		
		public SelectionListener(JLabel label){
			super();
			mCounterLabel = label;
			mCounter = 0;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange() == ItemEvent.SELECTED){
				mCounterLabel.setText(mCounterLabel.getText()
						.substring(0, mCounterLabel.getText().indexOf(':') + 2) + ++mCounter);		//I believe that in programming terms this is called magic xP
				
			}else if(e.getStateChange() == ItemEvent.DESELECTED){
				mCounterLabel.setText(mCounterLabel.getText()
						.substring(0, mCounterLabel.getText().indexOf(':') + 2) + --mCounter);
			}
		}
		
	}
}
