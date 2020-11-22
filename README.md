# KSJTools

KSJTools is tool for compute performance of spectral clustering based unsupervised classifier. It takes original arff file, result file, attribute name, and positive label value. 

Original arff file contains class label. Result file is text file with matrix, that have predicted results with spectral clustering based unsupervised classifier (get result from R previously). 

Before excute in R to get result txt file, we need arff file that removed classifier attribute. It is hard to remove all class value in every instance manually, this have a "-n" option. **To execute this option, "-f" option is neccessary.** The new file that removed classifier label is saved in the same directory of original arff file. The name of new file is assign as [ original file name+"-0".arff ]. 

## Options

-  -f,--arffFile (original arff file)   =>  argument recieves the original arff file path that has a defect classifier label 
- -l,--label (attribute name)  =>  argument as label (Class attrubite) name 
- -p,--poslabel (positive label value)  =>  String value of bug label as argument 
- -r,--resultFile (result file)  =>  argument recieves the text file that contains result from spectral classifier with R
- -n, --new  => Create a new arff file that removed class value in all instance 

## Example Tests

- to get performance result

```powershell
./KSJTools -f /Users/sujin/Desktop/ISEL/datasets/4.2.2_different_machine_learner/HM.arff -r /Users/sujin/Desktop/ISEL/datasets/4.2.2-removed_class_label/4.2.2-results/HM-0-result.txt -p buggy -l class
```

* to get new file that removed class value

```powershell
./KSJTools -f /Users/sujin/Desktop/ISEL/datasets/4.2.2_different_machine_learner/HM.arff -r -n
```

