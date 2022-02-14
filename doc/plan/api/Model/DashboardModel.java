package api.Model;

/**
 * Internal API used to update backend data values based on the DashboardView inputs from buttons
 * @author Brandon Bae
 */
public interface DashboardModel {

  /**
   * Queues an old instruction used previously by passing it to the compiler treating it like a raw input
   */
  public void queueOldInsn(String oldInsn);

}