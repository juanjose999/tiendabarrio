import java.util.*;

class Producto {
    String codigo;
    String nombre;
    double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
}

class Tienda {
    Map<String, Producto> inventario;
    List<Producto> carrito;

    public Tienda() {
        inventario = new HashMap<>();
        carrito = new ArrayList<>();
    }

    public void cargarInventario(String codigo, String nombre, double precio) {
        Producto producto = new Producto(codigo, nombre, precio);
        inventario.put(codigo, producto);
    }

    public Producto buscarProducto(String clave) {
        for (Producto producto : inventario.values()) {
            if (producto.codigo.equalsIgnoreCase(clave) || producto.nombre.equalsIgnoreCase(clave)) {
                return producto;
            }
        }
        return null;
    }

    public void agregarAlCarrito(Producto producto) {
        carrito.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        carrito.remove(producto);
    }

    public double calcularTotalCarrito() {
        double total = 0;
        for (Producto producto : carrito) {
            total += producto.precio;
        }
        return total * 1.19; // Incluye el 19% de IVA
    }

    public void realizarVenta() {
        double total = calcularTotalCarrito();
        System.out.println("Total de la venta (con IVA): $" + total);
        carrito.clear(); // Limpia el carrito después de la venta
    }

    public void mostrarInventario() {
        System.out.println("Inventario de la tienda:");
        for (Producto producto : inventario.values()) {
            System.out.println("Código: " + producto.codigo + " - Nombre: " + producto.nombre + " - Precio: $" + producto.precio);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        Scanner scanner = new Scanner(System.in);

        tienda.cargarInventario("P1", "cerveza", 4000);
        tienda.cargarInventario("P2", "pony malta", 2000);
        tienda.cargarInventario("P3", "galletas", 3000);
        tienda.cargarInventario("P4", "bareta", 10000);

        boolean continuar = true;
        while (continuar) {
            System.out.println("1. Agregar producto");
            System.out.println("2. eliminar prducto");
            System.out.println("3. Realizar venta");
            System.out.println("4. Ver todos los productos");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción (1-5): ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("agregar producto: ");
                    scanner.nextLine(); // Limpia el búfer
                    String claveProducto = scanner.nextLine();
                    Producto productoEncontrado = tienda.buscarProducto(claveProducto);
                    if (productoEncontrado != null) {
                        System.out.println("Producto encontrado: " + productoEncontrado.nombre + " - Precio: $" + productoEncontrado.precio);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 2:
                    System.out.print("Eliminar producto: ");
                    scanner.nextLine(); // Limpia el búfer
                    String claveCarrito = scanner.nextLine();
                    Producto productoCarrito = tienda.buscarProducto(claveCarrito);
                    if (productoCarrito != null) {
                        tienda.agregarAlCarrito(productoCarrito);
                        System.out.println("Producto agregado al carrito.");
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 3:
                    if (!tienda.carrito.isEmpty()) {
                        tienda.realizarVenta();
                    } else {
                        System.out.println("El carrito está vacío.");
                    }
                    break;
                case 4:
                    tienda.mostrarInventario();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        System.out.println("¡Gracias por usar la aplicación de la tienda!");
    }
}
