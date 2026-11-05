import java.util.Scanner;

public class VistaTerminal {
    private Scanner scanner;
    private TareaController controlador;
    
    public VistaTerminal(){
        this.scanner = new Scanner(System.in);
        this.controlador = new TareaController(new AlmacenamientoMemoria());
    }

    public void mostrarMenu() {

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- todolist Nuestra---");
            System.out.println( "1. Agregar tarea");
            System.out.println("2. Completar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.print("Descripción de la tarea: ");
                    String desc = scanner.nextLine();
                     System.out.print("Titulo de la tarea: ");
                    String titulo = scanner.nextLine();
                    controlador.agregarTarea(titulo, desc);
                    System.out.println("Tarea agregada.");
                    
                break;

                case "2":

                try {
                    System.out.println("ID de la tarea a completar: ");
                    int idACompletar = scanner.nextInt();
                    controlador.completarTarea(idACompletar);
                    scanner.nextLine();
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }

                break;

                case "3":
                    try {
                        System.out.println("Ingrese el ID de la tarea que eliminará: ");
                        int idAEliminar = scanner.nextInt();
                        controlador.eliminarTarea(idAEliminar);
                        scanner.nextLine();
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("No existe una tarea con ese ID ");
                    }
                    break;

                
                case "4":
                    salir = true;
                    break;
                   
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}



