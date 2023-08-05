package code.yousef.blog.shared.common.main.models


interface IProject {
    var id: String?
    var projectName: String
    var projectNameArabic: String
    var projectSlug: String?
    var projectHeader: String
    var projectDescription: String
    var projectDescriptionArabic: String
    var projectSummary: String
    var projectSummaryArabic: String
    var projectScreenShots: List<String>
    var github: String?
    var website: String?
    var playStore: String?
    var appStore: String?
    var twitter: String?
    var instagram: String?
}