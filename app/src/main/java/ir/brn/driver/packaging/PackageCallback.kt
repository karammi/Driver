package ir.brn.driver.packaging

import ir.brn.driver.model.core.Package

interface PackageCallback {

    fun onSelectedPackegeClick(currentPackage:Package)
}