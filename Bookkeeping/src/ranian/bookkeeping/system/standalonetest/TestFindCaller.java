package ranian.bookkeeping.system.standalonetest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class TestFindCaller {

	public static void main(String[] args) throws CoreException {

		IJavaProject project = getJavaProject("Bookkeeping");
		
		CallStackTest callGen = new CallStackTest();
		IType type = project.findType("ranian.bookkeeping.system.persistence.connection.impl.DatasourceConnectionUtil");

	    IMethod m = findMethod(type, "getMysqlConnection");
	    Set<IMethod> methods = new HashSet<IMethod>();
	    methods = callGen.getCallersOf(m);
	    for (Iterator<IMethod> i = methods.iterator(); i.hasNext();)
	    {
	        System.out.println(i.next().toString());
	    }
		
	}
	
	public static IMethod findMethod(IType type, String methodName) throws JavaModelException
	{
	    //IType type = project.findType(typeName);

	    IMethod[] methods = type.getMethods();
	    IMethod theMethod = null;

	    for (int i = 0; i < methods.length; i++)
	    {
	        IMethod imethod = methods[i];
	        if (imethod.getElementName().equals(methodName)) {
	            theMethod = imethod;
	        }
	    }

	    if (theMethod == null)
	    {           
	        System.out.println("Error, method" + methodName + " not found");
	        return null;
	    }

	    return theMethod;
	}
	
	public static IJavaProject getJavaProject(String projectName) throws CoreException
	{
	    IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
	    IProject project= root.getProject(projectName);
	    if (!project.exists()) {
	        project.create(null);
	    } else {
	        project.refreshLocal(IResource.DEPTH_INFINITE, null);
	    }

	    if (!project.isOpen()) {
	        project.open(null);
	    }

	    IJavaProject jproject= JavaCore.create(project);

	    return jproject;    
	}
}
