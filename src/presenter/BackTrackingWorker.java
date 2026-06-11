package presenter;

import javax.swing.SwingWorker;

import model.BackTracking;
import model.Equipo;

public class BackTrackingWorker extends SwingWorker < Equipo  ,Void > {
	private MenuPresenter presenter;
    private BackTracking algoritmo;
    
    public BackTrackingWorker(MenuPresenter presenter, BackTracking algoritmo) {
        this.presenter = presenter;
        this.algoritmo = algoritmo;
    }
	@Override
	protected Equipo doInBackground() throws Exception {
		algoritmo.resolver();
		return algoritmo.getEquipoFinal();
		
	}
	@Override
    protected void done() {
        try {
            
            Equipo mejorEquipo = get();
            
            presenter.finalizarCreacionEquipo(mejorEquipo, algoritmo.getTiempoTotal());
            
        } catch (Exception ex) {
       
            presenter.mostrarErrorAlgoritmo(ex);
        }
    }

}
