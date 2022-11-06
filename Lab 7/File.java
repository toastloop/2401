// It's importing the IOException class from the java.io package.
import java.io.IOException;
// It's importing the Charset class from the java.nio.charset package.
import java.nio.charset.Charset;
// It's importing the StandardCharsets class from the java.nio.charset package.
import java.nio.charset.StandardCharsets;
// It's importing the Files class from the java.nio.file package.
import java.nio.file.Files;
// It's importing the Path class from the java.nio.file package.
import java.nio.file.Path;
// It's importing the Paths class from the java.nio.file package.
import java.nio.file.Paths;
// It's importing the StandardOpenOption class from the java.nio.file package.
import java.nio.file.StandardOpenOption;
// It's importing the List class from the java.util package.
import java.util.List;

/**
 * It's a wrapper for the Java NIO2 API
 */
public class File implements AutoCloseable {
    // Used to store the name of the file.
    private String filename = null;
    // Used to store the character set of the file.
    private Charset charset = null;
    // A private variable that is used to store the path of the file.
    private Path path = null;
    // A private variable that is used to store the absolute path of the file.
    private Path absolute = null;

    // It's a constructor.
    File(String filename) {
        this.filename = filename;
        this.charset = StandardCharsets.UTF_8;
        this.path = Paths.get(this.filename);
        this.absolute = path.toAbsolutePath();
    }
    // It's a constructor.
    File(String filename, Charset charset) {
        this.filename = filename;
        this.charset = charset;
        this.path = Paths.get(this.filename);
        this.absolute = path.toAbsolutePath();
    }


    /**
     * It creates a file at the path specified by the path variable
     * 
     * @return A Path object.
     */
    public Path create() throws IOException {
        return Files.createFile(this.path);
    }

    /**
     * Creates a temporary file in the default temporary-file directory, using the given prefix and
     * suffix to generate its name.
     * 
     * @param prefix The prefix string to be used in generating the file's name; may be null
     * @param suffix The suffix string to be used in generating the file's name; may be null, in which
     * case the suffix ".tmp" will be used
     * @return A Path object
     */
    public Path temp(String prefix, String suffix) throws IOException {
        return Files.createTempFile(prefix, suffix);
    }

    /**
     * Copy the file at this.path to the file at the path specified by the filename parameter.
     * 
     * @param filename The name of the file to copy to.
     * @return A Path object.
     */
    public Path copy(String filename) throws IOException {
        return Files.copy(this.path, Paths.get(filename));
    }

    /**
     * It moves the file from the current path to the new path
     * 
     * @param filename The name of the file to be moved.
     * @return A Path object.
     */
    public Path move(String filename) throws IOException {
        return Files.move(this.path, Paths.get(filename));
    }

    /**
     * It deletes the file at the path specified by the `path` variable
     * 
     * @return A boolean value.
     */
    public boolean delete() throws IOException {
        Files.delete(this.path);
        return Files.notExists(this.path);
    }

    /**
     * Create a hard link to the file at the given path.
     * 
     * @param filename The name of the file to create.
     * @return A Path object.
     */
    public Path link(String filename) throws IOException {
        return Files.createLink(Paths.get(filename), this.path);
    }

    /**
     * It creates a symbolic link to the file represented by this object
     * 
     * @param filename The name of the symbolic link to create.
     * @return A Path object.
     */
    public Path symlink(String filename) throws IOException {
        return Files.createSymbolicLink(Paths.get(filename), this.path);
    }

    /**
     * It returns the size of the file in bytes
     * 
     * @return The size of the file in bytes.
     */
    public long size() throws IOException {
        return Files.size(this.path);
    }

    /**
     * It returns the MIME type of the file
     * 
     * @return The type of the file.
     */
    public String type() throws IOException {
        return Files.probeContentType(this.path);
    }

    /**
     * Get the owner of the file at the path represented by this object.
     * 
     * @return The name of the owner of the file.
     */
    public String owner() throws IOException {
        return Files.getOwner(this.path).getName();
    }

    /**
     * It returns the last modified time of the file as a string
     * 
     * @return The last modified time of the file.
     */
    public String modified() throws IOException {
        return Files.getLastModifiedTime(this.path).toString();
    }

    /**
     * It returns the permissions of the file as a string
     * 
     * @return The permissions of the file.
     */
    public String permissions() throws IOException {
        return Files.getPosixFilePermissions(this.path).toString();
    }

    /**
     * It reads all the attributes of a file and returns them as a string
     * 
     * @return The attributes of the file.
     */
    public String attributes() throws IOException {
        return Files.readAttributes(this.path, "*").toString();
    }

    /**
     * This function returns the path of the file
     * 
     * @return The path of the file.
     */
    public String path() {
        return this.path.toString();
    }

    /**
     * This function returns the absolute path of the file
     * 
     * @return The absolute path of the file.
     */
    public String absolute() {
        return this.absolute.toString();
    }

    /**
     * This function returns true if the file exists, false otherwise
     * 
     * @return A boolean value.
     */
    public boolean exists() {
        return Files.exists(this.path);
    }

    /**
     * It checks if the file is readable.
     * 
     * @return A boolean value.
     */
    public boolean readable() {
        return Files.isReadable(this.path);
    }

    /**
     * This function returns true if the file is writable
     * 
     * @return A boolean value.
     */
    public boolean writable() {
        return Files.isWritable(this.path);
    }

    /**
     * `returns true if the file is executable`
     * 
     * @return A boolean value.
     */
    public boolean executable() {
        return Files.isExecutable(this.path);
    }

    /**
     * This function returns true if the file is hidden, false otherwise
     * 
     * @return A boolean value.
     */
    public boolean hidden() throws IOException {
        return Files.isHidden(this.path);
    }

    /**
     * It reads all the lines from a file and returns them as a list of strings
     * 
     * @return A list of strings.
     */
    public List<String> read() throws IOException {
        return Files.readAllLines(this.absolute, this.charset);
    }

    /**
     * Append the given lines to the file at the given path
     * 
     * @param lines The lines to write to the file.
     * @return A Path object.
     */
    public Path append(List<String> lines) throws IOException {
        return Files.write(this.path, lines, this.charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
     * It writes a list of strings to a file
     * 
     * @param lines The lines to write to the file.
     * @return A Path object.
     */
    public Path write(List<String> lines) throws IOException {
        return Files.write(this.path, lines, this.charset, StandardOpenOption.CREATE);
    }

    /**
     * It returns nothing.
     */
    public void close() {
        return;
    }
}