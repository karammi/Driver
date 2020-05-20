package ir.brn.driver.packaging

import ir.brn.driver.model.core.Package

interface PackageListener {
    fun onSelectedPackage(currentPackage: Package)
}