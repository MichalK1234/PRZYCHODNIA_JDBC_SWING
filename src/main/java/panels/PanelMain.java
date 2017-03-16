package panels;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class PanelMain extends JPanel implements ActionListener {

	private PanelDoctor panelDoctor;
	private PanelPatient panelPatient;
	private PanelStatistics panelStatistics;

	private final static String PANEL_DOCTOR = "PANEL_DOCTOR";
	private final static String PANEL_PATIENT = "PANEL_PATIENT";
	private final static String PANEL_REGISTER = "PANEL_REGISTER";
	private final static String PANEL_FILTER = "PANEL_FILTER";
	private final static String PANEL_STATISTICS = "PANEL_STATISTICS";

	public PanelMain() throws SQLException {
		super(new CardLayout());
		this.panelDoctor = new PanelDoctor();
		this.panelPatient = new PanelPatient();
		this.panelStatistics = new PanelStatistics();

		add(panelDoctor, PANEL_DOCTOR);
		add(panelPatient, PANEL_PATIENT);
		add(panelStatistics, PANEL_STATISTICS);

	}

	public JMenuBar createJMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuPanele = new JMenu("WINDOWS");
		menuPanele.setMnemonic('W');

		JMenuItem menuItemPanelDoctor = new JMenuItem("Doctor");
		menuItemPanelDoctor.addActionListener(this);
		menuItemPanelDoctor.setActionCommand(PANEL_DOCTOR);
		menuItemPanelDoctor.setMnemonic('D');
		menuItemPanelDoctor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));

		JMenuItem menuItemPanelPatient = new JMenuItem("Patient");
		menuItemPanelPatient.addActionListener(this);
		menuItemPanelPatient.setActionCommand(PANEL_PATIENT);
		menuItemPanelPatient.setMnemonic('P');
		menuItemPanelPatient.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));

		JMenuItem menuItemPanelRegister = new JMenuItem("Register");
		menuItemPanelRegister.addActionListener(this);
		menuItemPanelRegister.setActionCommand(PANEL_REGISTER);
		menuItemPanelRegister.setMnemonic('R');
		menuItemPanelRegister.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));

		JMenuItem menuItemPanelFilter = new JMenuItem("Filter");
		menuItemPanelFilter.addActionListener(this);
		menuItemPanelFilter.setActionCommand(PANEL_FILTER);
		menuItemPanelFilter.setMnemonic('F');
		menuItemPanelFilter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));

		JMenuItem menuItemPanelStatistics = new JMenuItem("Statistics");
		menuItemPanelStatistics.addActionListener(this);
		menuItemPanelStatistics.setActionCommand(PANEL_STATISTICS);
		menuItemPanelStatistics.setMnemonic('S');
		menuItemPanelStatistics.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));

		menuPanele.add(menuItemPanelDoctor);
		menuPanele.addSeparator();
		menuPanele.add(menuItemPanelPatient);
		menuPanele.addSeparator();
		menuPanele.add(menuItemPanelRegister);
		menuPanele.addSeparator();
		menuPanele.add(menuItemPanelFilter);
		menuPanele.addSeparator();
		menuPanele.add(menuItemPanelStatistics);

		menuBar.add(menuPanele);

		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(PANEL_DOCTOR)) {
			CardLayout cl = (CardLayout) getLayout();
			cl.show(this, PANEL_DOCTOR);

		} else if (e.getActionCommand().equals(PANEL_PATIENT)) {
			CardLayout cl = (CardLayout) getLayout();
			cl.show(this, PANEL_PATIENT);
		} else if (e.getActionCommand().equals(PANEL_REGISTER)) {
			try {
				createRegistrationWindow();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals(PANEL_FILTER)) {

			try {
				createFilterWindow();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals(PANEL_STATISTICS)) {

			panelStatistics.updateCb();
			CardLayout cl = (CardLayout) getLayout();
			cl.show(this, PANEL_STATISTICS);
		}
	}

	public void createRegistrationWindow() throws SQLException {
		JFrame frame = new JFrame("Registration");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelRegister panel = new PanelRegister();
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public void createFilterWindow() throws SQLException {
		JFrame frame = new JFrame("Filter");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PanelFilter panel = new PanelFilter();
		panel.setVisible(true);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

}
