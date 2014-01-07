scala-project4
==============

1 Problem denition
Debugging distributed programs is a real challenge. As we discussed in class,
logging information about what actors send what message to what other actor
can help in guring out problems. This project consists in two phases. In phase
I, you have to add logging capabilities to Scala so that information is captured
about the execution. In phase II you have to use these logs to produce useful
information for the user to gure out what could have gone wrong.

2 Requirements for Phase I
For phase I, you have to add logging to Scala actors. You can choose to do this
any way you like but you will use the information for phase II. A typical trace
will consists in simple text information in log files - one log file per actor might
interfere less with the execution. This way, the logs can be processed using any
tools/language.
As part of the phase I, you have to implement the logging facility and to
write (or reuse) a mid-complexity actor program and to trace its execution using
your facility.

1.Programs Write your logging code in actorlog.scala and write an example
execution in example.scala. The program example.scala should run without
arguments and produce a traced execution.

2 Requirements for Phase II
In phase II you have to demonstrate an interesting use of the logs produced in
phase I. Examples are:

1. Allow Causeway to read the logs you produce to be able to trace visually
the execution. Add other debugging facilities such as summary statistics.

2. Visualize using MSCGen the message exchange. You have to deal with
tens-hundreds of actors.

3. Visualize the call graph using a .dot le processor like Graphwiz.

4. Specify conditions that need to be true for the message exchange and
check that they hold (use some high level language like Prolog, ML or
even Scala). A Scala based solution would be particularly nice.

5. Find other software (free or commercial) that can be used to deal with
the problem
