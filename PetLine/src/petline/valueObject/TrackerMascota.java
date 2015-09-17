package petline.valueObject;



public class TrackerMascota {

	private int idTrackerMascota;
	private int tempActual;
	private int tempMinima;
	private int tempMaxima;
	private Mascota mascota;
	private Tracker tracker;

	public int getIdTrackerMascota() {
		return idTrackerMascota;
	}

	public void setIdTrackerMascota(int idTrackerMascota) {
		this.idTrackerMascota = idTrackerMascota;
	}

	public int getTempActual() {
		return tempActual;
	}

	public void setTempActual(int tempActual) {
		this.tempActual = tempActual;
	}

	public int getTempMinima() {
		return tempMinima;
	}

	public void setTempMinima(int tempMinima) {
		this.tempMinima = tempMinima;
	}

	public int getTempMaxima() {
		return tempMaxima;
	}

	public void setTempMaxima(int tempMaxima) {
		this.tempMaxima = tempMaxima;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

}
